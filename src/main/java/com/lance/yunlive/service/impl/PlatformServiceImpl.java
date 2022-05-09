package com.lance.yunlive.service.impl;

import com.lance.yunlive.common.api.ApiClient;
import com.lance.yunlive.common.enums.Platform;
import com.lance.yunlive.config.ApiClientFactory;
import com.lance.yunlive.domain.LiveRoom;
import com.lance.yunlive.service.PlatformService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class PlatformServiceImpl implements PlatformService {

    @Resource
    ApiClientFactory apiClientFactory;

    @Override
    @Async("taskExecutor")
    public CompletableFuture<List<LiveRoom>> getRecByPlatformAsync(String platform, int page, int size) {
        log.info("平台：" + platform + " 开始异步获取平台推荐内容");
        List<LiveRoom> recs = new ArrayList<>();
        try {
            recs = getRecByPlatform(platform, page, size);
        } catch (Exception e) {
            log.warn("平台：" + platform + "请求接口出错");
        }
        log.info("平台：" + platform + " 获取到 " + recs.size() + "条数据");
        return CompletableFuture.completedFuture(recs);
    }

    @Override
    public List<LiveRoom> getRecByPlatform(String platform, int page, int size) {
        ApiClient apiClient = checkClient(platform);
        if (apiClient == null) {
            return new ArrayList<>();
        }
        return apiClient.getRecommend(page, size);
    }

    public ApiClient checkClient(String platform) {
        Platform platformEnum = null;
        switch (platform) {
            case "bilibili":
                platformEnum = Platform.BILIBILI;
                break;
            case "douyu":
                platformEnum = Platform.DOUYU;
                break;
            case "huya":
                platformEnum = Platform.HUYA;
                break;
            case "cc":
                platformEnum = Platform.CC;
                break;
            case "egame":
                platformEnum = Platform.EGAME;
                break;
        }
        if (platformEnum == null) return null;
        return apiClientFactory.getApiType(platformEnum);
    }
}
