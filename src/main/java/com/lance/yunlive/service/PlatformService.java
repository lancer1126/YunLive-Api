package com.lance.yunlive.service;

import com.lance.yunlive.domain.LiveQuality;
import com.lance.yunlive.domain.LiveRoom;

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
}
