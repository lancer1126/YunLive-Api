package com.lance.yunlive.common.api;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.lance.yunlive.common.constrants.ApiUrl;
import com.lance.yunlive.common.constrants.HttpInfo;
import com.lance.yunlive.common.enums.BiliQuality;
import com.lance.yunlive.common.enums.Platform;
import com.lance.yunlive.domain.vo.Area;
import com.lance.yunlive.domain.vo.LiveQuality;
import com.lance.yunlive.domain.vo.LiveRoom;
import com.lance.yunlive.domain.vo.Streamer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class BilibiliApiService implements ApiClient {

    @Override
    public Platform getPlatform() {
        return Platform.BILIBILI;
    }

    @Override
    public String getPlatformName() {
        return Platform.BILIBILI.name;
    }

    /**
     * 分页获取推荐内容
     */
    @Override
    public List<LiveRoom> getRecommend(int page, int size) {
        List<LiveRoom> liveRoomList = new ArrayList<>();
        String url = String.format(ApiUrl.Bilibili.RECOMMEND, page, size);
        String content = HttpUtil.get(url);
        if (StrUtil.isEmpty(content)) {
            log.warn("从bilibili获取的推荐内容为空");
            return liveRoomList;
        }

        JSONObject jsonObject = JSON.parseObject(content);
        if (jsonObject.getInteger("code") != 0) {
            log.warn("bilibili获取直播间推荐内容异常");
            return liveRoomList;
        }

        JSONArray data = jsonObject.getJSONArray("data");
        for (Object item : data) {
            JSONObject itemObj = (JSONObject) item;

            LiveRoom liveRoom = new LiveRoom();
            liveRoom.setPlatform(getPlatformName());
            liveRoom.setRoomId(itemObj.getString("roomid"));
            liveRoom.setCategoryId(itemObj.getString("area"));
//            liveRoom.setCategoryName(getSingleRoomInfo(itemObj.getString("roomid")).getCategoryName());
            liveRoom.setRoomName(itemObj.getString("title"));
            liveRoom.setOwnerName(itemObj.getString("uname"));
            liveRoom.setRoomPic(itemObj.getString("system_cover"));
            liveRoom.setOwnerHeadPic(itemObj.getString("face"));
            liveRoom.setOnline(itemObj.getInteger("online"));
            liveRoom.setIsLive(1);
            liveRoomList.add(liveRoom);
        }
        return liveRoomList;
    }

    /**
     * 获取b站单个直播间信息
     */
    @Override
    public LiveRoom getSingleRoomInfo(String roomId) {
        LiveRoom liveRoom = new LiveRoom();
        try {
            String url = String.format(ApiUrl.Bilibili.SINGLE_ROOM, roomId);
            String content = HttpRequest.get(url)
                    .header(Header.USER_AGENT, HttpInfo.USER_AGENT)
                    .execute().body();

            JSONObject dataObj = JSON.parseObject(content).getJSONObject("data");
            JSONObject roomInfo = dataObj.getJSONObject("room_info");
            JSONObject baseInfo = dataObj.getJSONObject("anchor_info").getJSONObject("base_info");

            liveRoom.setPlatform(getPlatformName());
            liveRoom.setRoomId(roomInfo.getString("room_id"));
            liveRoom.setCategoryId(roomInfo.getInteger("area_id").toString());
            liveRoom.setCategoryName(roomInfo.getString("area_name"));
            liveRoom.setRoomName(roomInfo.getString("title"));
            liveRoom.setOwnerName(baseInfo.getString("uname"));
            liveRoom.setRoomPic(roomInfo.getString("cover"));
            liveRoom.setOwnerHeadPic(baseInfo.getString("face"));
            liveRoom.setOnline(roomInfo.getInteger("online"));
            liveRoom.setIsLive((roomInfo.getInteger("live_status") == 1) ? 1 : 0);
        } catch (Exception e) {
            log.error("BILIBILI---获取直播间信息异常---roomId：" + roomId + "\n" + e);
        }
        return liveRoom;
    }

    @Override
    public LiveQuality getRealUrl(String roomId) {
        LiveQuality liveQuality = new LiveQuality();
        // 获取当前直播间的状态与真实播放地址
        JSONObject roomInfo = getRealRid(roomId);
        if (roomInfo == null) {
            liveQuality.setStatus("noeExist");
            return liveQuality;
        }
        Boolean liveStatus = roomInfo.getBoolean("live_status");
        if (liveStatus == null || !liveStatus) {
            liveQuality.setStatus("offline");
        } else {
            liveQuality.setStatus("live");
        }

        String realId = roomInfo.getString("room_id");
        List<BiliQuality> qualityList = new ArrayList<>(Arrays.asList(BiliQuality.values()));
        // 获取所有画质下真实的播放地址
        for (BiliQuality quality : qualityList) {
            String playUrl = getPlayUrl(realId, quality.rate);
            switch (quality) {
                case FD:
                    liveQuality.setFd(playUrl);
                    break;
                case HD:
                    liveQuality.setHd(playUrl);
                    break;
                case LD:
                    liveQuality.setLd(playUrl);
                    break;
                case SD:
                    liveQuality.setSd(playUrl);
                    break;
                case OD:
                    liveQuality.setOd(playUrl);
                    break;
            }
        }
        return liveQuality;
    }

    @Override
    public List<Streamer> search(String keyWord) {
        List<Streamer> streamerList = new ArrayList<>();
        String url = String.format(ApiUrl.Bilibili.SEARCH, keyWord);
        String content = HttpUtil.get(url);
        JSONObject jsonObject = JSON.parseObject(content);
        if (jsonObject.getInteger("code") == 0) {
            JSONArray resArr = jsonObject.getJSONObject("data").getJSONArray("result");
            for (int i = 0; i < resArr.size(); i++) {
                JSONObject obj = resArr.getJSONObject(i);
                Streamer streamer = new Streamer();
                streamer.setPlatform(Platform.BILIBILI.name);
                streamer.setNickName(parseUsername(obj.getString("uname")));
                streamer.setCateName(obj.getString("cate_name"));
                streamer.setHeadPic(obj.getString("uface"));
                streamer.setRoomId(obj.getString("roomid"));
                streamer.setIsLive(obj.getBoolean("is_live") ? "1" : "0");
                streamer.setFollowers(obj.getInteger("attentions"));
                streamerList.add(streamer);
            }
        } else {
            log.error("查询内容为空，关键字: " + keyWord);
        }
        return streamerList;
    }

    @Override
    public List<Area> getAreas() {
        String url = ApiUrl.Bilibili.AREA;
        String content = HttpUtil.get(url);

        List<Area> areaList = new ArrayList<>();
        try {
            JSONObject jsonObject = JSON.parseObject(content);
            JSONArray data = jsonObject.getJSONObject("data").getJSONArray("data");
            for (int i = 0; i < data.size(); i++) {
                JSONObject areaGroup = data.getJSONObject(i);
                JSONArray areaInfo = areaGroup.getJSONArray("list");
                for (int subIndex = 0; subIndex < areaInfo.size(); subIndex++) {
                    JSONObject subArea = areaInfo.getJSONObject(subIndex);

                    Area area = new Area()
                            .setAreaType(subArea.getString("parent_id"))
                            .setTypeName(subArea.getString("parent_name"))
                            .setAreaId(subArea.getString("id"))
                            .setAreaName(subArea.getString("name"))
                            .setAreaPic(subArea.getString("pic"))
                            .setPlatform(Platform.BILIBILI.name);
                    areaList.add(area);
                }
            }
        } catch (Exception e) {
            log.error("获取Bilibili分类列表错误");
        }
        return areaList;
    }

    public JSONObject getRealRid(String rid) {
        String url = String.format(ApiUrl.Bilibili.ROOM_INIT, rid);
        String content = HttpRequest.get(url)
                .header(Header.USER_AGENT, HttpInfo.USER_AGENT)
                .execute().body();

        JSONObject resp = JSON.parseObject(content);
        Integer code = resp.getInteger("code");
        if (code != 0) {
            log.error("Bilibili获取直播间真实地址错误, roomId: " + rid);
            return null;
        }

        JSONObject data = resp.getJSONObject("data");
        JSONObject obj = new JSONObject();
        obj.put("live_status", data.getBoolean("live_status"));
        obj.put("room_id", data.getString("room_id"));
        return obj;
    }

    /**
     * 获取真实的播放地址
     *
     * @param roomId 房间Id
     * @param qn     画质选项
     * @return 地址
     */
    private String getPlayUrl(String roomId, String qn) {
        String url = String.format(ApiUrl.Bilibili.PLAY_URL, roomId, qn);
        String content = HttpRequest.get(url)
                .header(Header.USER_AGENT, HttpInfo.USER_AGENT)
                .execute().body();

        JSONArray durlArr = JSON.parseObject(content).getJSONObject("data").getJSONArray("durl");
        if (!durlArr.isEmpty()) {
            return durlArr.getJSONObject(0).getString("url");
        } else {
            log.error("Bilibili---获取真实地址异常---roomId：" + roomId);
            return "获取失败";
        }
    }

    /**
     * 查询内容中会包含css属性，需要去掉
     */
    private String parseUsername(String username) {
        return username.replaceAll("<em class=\"keyword\">", "")
                .replaceAll("</em>", "");
    }

}
