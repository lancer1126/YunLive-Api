package com.lance.yunlive.common.api;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.lance.yunlive.common.constrants.ApiUrl;
import com.lance.yunlive.common.constrants.Global;
import com.lance.yunlive.common.enums.Platform;
import com.lance.yunlive.common.utils.CommonUtil;
import com.lance.yunlive.domain.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class DouyuApiService implements ApiClient {
    @Override
    public Platform getPlatform() {
        return Platform.DOUYU;
    }

    @Override
    public String getPlatformName() {
        return Platform.DOUYU.name;
    }

    @Override
    public List<LiveRoom> getRecommend(int page, int size) {
        List<LiveRoom> liveRoomList = new ArrayList<>();
        String url = String.format(ApiUrl.Douyu.RECOMMEND, page, "");
        // Douyu的此api一次固定获取8条内容
        while (liveRoomList.size() < size) {
            String content = HttpUtil.get(url);
            JSONObject jsonObject = JSON.parseObject(content);
            if (jsonObject.getInteger("code").equals(0)) {
                JSONArray dataList = jsonObject.getJSONObject("data").getJSONArray("list");
                for (Object obj : dataList) {
                    JSONObject item = (JSONObject) obj;
                    LiveRoom liveRoom = new LiveRoom();
                    liveRoom.setPlatform(Platform.DOUYU.name);
                    liveRoom.setRoomId(item.getInteger("rid").toString());
                    liveRoom.setCategoryId(item.getString("cate2Id"));
                    liveRoom.setCategoryName(Global.DouyuCateMap.get(item.getString("cate2Id")));
                    liveRoom.setRoomName(item.getString("roomName"));
                    liveRoom.setOwnerName(item.getString("nickname"));
                    liveRoom.setRoomPic(item.getString("roomSrc"));
                    liveRoom.setOwnerHeadPic(item.getString("avatar"));
                    liveRoom.setOnline(CommonUtil.NumStrToInt(item.getString("hn")));
                    liveRoom.setIsLive(item.getInteger("isLive"));

                    if (liveRoomList.size() < size) {
                        liveRoomList.add(liveRoom);
                    } else {
                        break;
                    }
                }
            }
        }
        return liveRoomList;
    }

    @Override
    public LiveRoom getSingleRoomInfo(String roomId) {
        return null;
    }

    @Override
    public LiveQuality getRealUrl(String roomId) {
        return null;
    }

    @Override
    public List<Streamer> search(String keyWord) {
        return null;
    }

    @Override
    public List<AreaGroup> getAreas() {
        return null;
    }
}
