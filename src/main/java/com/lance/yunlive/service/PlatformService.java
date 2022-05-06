package com.lance.yunlive.service;

import com.lance.yunlive.domain.LiveRoom;

import java.util.List;
import java.util.concurrent.Future;

public interface PlatformService {

    /**
     * 异步获取平台推荐内容
     */
    Future<List<LiveRoom>> getRecByContentAsync(String platform, int page, int size);

    /**
     * 获取平台推荐内容
     */
    List<LiveRoom> getRecContent(String platform, int page, int size);
}