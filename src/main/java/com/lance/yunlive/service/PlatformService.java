package com.lance.yunlive.service;

import com.lance.yunlive.domain.vo.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface PlatformService {

    /**
     * 异步获取平台推荐内容
     */
    CompletableFuture<List<LiveRoom>> getRecByPlatformAsync(String platform, int page, int size);

    /**
     * 获取平台推荐内容
     */
    List<LiveRoom> getRecByPlatform(String platform, int page, int size);

    /**
     * 获取一个直播间的详细信息
     */
    LiveRoom getRoomInfo(String uid, String platform, String roomId);

    /**
     * 获取每个画质的真实地址
     */
    LiveQuality getRealUrl(String platform, String roomId);

    /**
     * 根据平台以及关键字查询主播
     */
    List<Streamer> search(String platform, String keyWord);

    /**
     * 异步根据平台名以及关键字查询主播
     */
    CompletableFuture<List<Streamer>> searchAsync(String platform, String keyWord);

    /**
     * 获取各个平台的分类列表
     */
    List<AreaGroup> getAreas(String platform);

    /**
     * 根据平台以及groupId获取推荐的直播间列表
     */
    List<LiveRoom> getRecByGroupOrArea(String platform, Area area, int page);
}
