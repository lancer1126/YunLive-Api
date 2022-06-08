package com.lance.yunlive.service;

import com.lance.yunlive.domain.vo.*;

import java.util.List;

public interface LiveRoomService {

    /**
     * 多线程获取多个平台推荐内容
     */
    List<LiveRoom> getRecommend(int page, int size);

    /**
     * 获取单个平台推荐内容
     */
    List<LiveRoom> getRecommendByPlatform(String platform, int page, int size);

    LiveRoom getRoomInfo(String uid, String platform, String roomId);

    LiveQuality getRealUrl(String platform, String roomId);

    List<Streamer> search(String platform, String keyWord);

    List<AreaGroup> getAreaByPlatform(String platform);
}
