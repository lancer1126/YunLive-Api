package com.lance.yunlive.service;

import com.lance.yunlive.domain.LiveRoom;

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
}
