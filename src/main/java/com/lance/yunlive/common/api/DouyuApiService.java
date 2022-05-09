package com.lance.yunlive.common.api;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lance.yunlive.common.constrants.ApiUrl;
import com.lance.yunlive.common.constrants.Global;
import com.lance.yunlive.common.enums.Platform;
import com.lance.yunlive.common.utils.CommonUtil;
import com.lance.yunlive.domain.LiveRoom;
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
        // Douyu的此api一次固定获取8条内容
        String url = String.format(ApiUrl.Douyu.RECOMMEND, page, "");
        String content = HttpUtil.get(url);
        JSONObject jsonObject = JSON.parseObject(content);
        if (jsonObject.getInteger("code").equals(0)) {
            JSONArray dataList = jsonObject.getJSONObject("data").getJSONArray("list");
            for (Object obj : dataList) {
                JSONObject item = (JSONObject) obj;
                LiveRoom liveRoom = new LiveRoom();
                liveRoom.setPlatForm(Platform.DOUYU.name);
                liveRoom.setRoomId(item.getInteger("rid").toString());
                liveRoom.setCategoryId(item.getString("cate2Id"));
                liveRoom.setCategoryName(Global.DouyuCateMap.get(item.getString("cate2Id")));
                liveRoom.setRoomName(item.getString("roomName"));
                liveRoom.setOwnerName(item.getString("nickname"));
                liveRoom.setRoomPic(item.getString("roomSrc"));
                liveRoom.setOwnerHeadPic(item.getString("avatar"));
                liveRoom.setOnline(CommonUtil.NumStrToInt(item.getString("hn")));
                liveRoom.setIsLive(item.getInteger("isLive"));
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