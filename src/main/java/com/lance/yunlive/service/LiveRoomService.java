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

    /**
     * 根据平台以及groupId获取推荐的直播间列表
     */
    List<LiveRoom> getRecByGroup(String platform, String groupId, int page);

    List<LiveRoom> getRecByArea(String platform, String groupId, String areaId, int page);
}
