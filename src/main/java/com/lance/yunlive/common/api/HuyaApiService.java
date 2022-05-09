package com.lance.yunlive.common.api;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lance.yunlive.common.constrants.ApiUrl;
import com.lance.yunlive.common.enums.Platform;
import com.lance.yunlive.domain.LiveRoom;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HuyaApiService implements ApiClient {
    @Override
    public Platform getPlatform() {
        return Platform.HUYA;
    }

    @Override
    public String getPlatformName() {
        return Platform.HUYA.name;
    }

    @Override
    public List<LiveRoom> getRecommend(int page, int size) {
        List<LiveRoom> liveRoomList = new ArrayList<>();
        String url = String.format(ApiUrl.Huya.RECOMMEND, page);

        String content = HttpUtil.get(url);
        JSONObject jsonObject = JSON.parseObject(content);
        if (jsonObject.getInteger("status").equals(200)) {
            JSONArray dataList = jsonObject.getJSONObject("data").getJSONArray("datas");
            for (Object obj : dataList) {
                JSONObject item = (JSONObject) obj;
                LiveRoom liveRoom = new LiveRoom();
                liveRoom.setPlatForm(Platform.HUYA.name);
                liveRoom.setRoomId(item.getString("profileRoom"));
                liveRoom.setCategoryId(item.getString("gid"));
                liveRoom.setCategoryName(item.getString("gameFullName"));
                liveRoom.setRoomName(item.getString("roomName"));
                liveRoom.setOwnerName(item.getString("nick"));
                liveRoom.setRoomPic(item.getString("screenshot"));
                liveRoom.setOwnerHeadPic(item.getString("avatar180"));
                liveRoom.setOnline(Integer.parseInt(item.getString("totalCount")));
                liveRoom.setIsLive(1);
                liveRoomList.add(liveRoom);
            }
        }
        return liveRoomList;
    }

    @Override
    public LiveRoom getSingleRoomInfo(String roomId) {
        return null;
    }
}
