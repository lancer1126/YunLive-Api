package com.lance.yunlive.common.api;

import com.lance.yunlive.common.enums.Platform;
import com.lance.yunlive.domain.LiveRoom;

import java.util.List;

public interface ApiClient {

    /**
     * 判断实现类的平台
     */
    Platform getPlatform();

    /**
     * 获取String类型的平台名
     * @return
     */
    String getPlatformName();

    /**
     * 获取推荐内容
     */
    List<LiveRoom> getRecommend(int page, int size);

    /**
     * 根据roomId获取单个房间信息
     */
    LiveRoom getSingleRoomInfo(String roomId);
}
