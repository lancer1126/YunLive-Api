package com.lance.yunlive.common.api;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lance.yunlive.common.enums.Platform;
import com.lance.yunlive.common.utils.HttpUtil;
import com.lance.yunlive.domain.LiveRoom;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class BilibiliApi extends ApiClient {

    public static BilibiliApi getInstance() {
        return BilibiliApiHolder.INSTANCE;
    }

    /**
     * 分页获取推荐内容
     */
    public List<LiveRoom> getRecommend(int page, int size) {
        List<LiveRoom> liveRoomList = new ArrayList<>();

        String url = "https://api.live.bilibili.com/room/v1/room/get_user_recommend?page=" + page + "&page_size=" + size;
        String result = HttpUtil.doGet(url);
        if (StrUtil.isEmpty(result)) {
            log.warn("从bilibili获取的推荐内容为空");
            return liveRoomList;
        }

        JSONObject jsonObject = JSON.parseObject(result);
        if (jsonObject.getInteger("code") != 0) {
            log.warn("bilibili获取直播间推荐内容异常");
            return liveRoomList;
        }

        JSONArray data = jsonObject.getJSONArray("data");
        for (Object item : data) {
            JSONObject itemObj = (JSONObject) item;

            LiveRoom liveRoom = new LiveRoom();
            liveRoom.setPlatForm(Platform.BILIBILI.name);
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

    public LiveRoom getSingleRoomInfo(String roomId) {
        return new LiveRoom();
    }

    private static class BilibiliApiHolder {
        private static final BilibiliApi INSTANCE = new BilibiliApi();
    }
}
