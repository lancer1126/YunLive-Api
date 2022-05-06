package com.lance.yunlive.config;

import com.lance.yunlive.common.api.ApiClient;
import com.lance.yunlive.common.enums.Platform;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 添加所有ApiClient的实现类
 */
@Component
public class ApiClientFactory implements ApplicationContextAware {

    private final static Map<Platform, ApiClient> API_CLIENT_MAP = new HashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, ApiClient> apiClients = applicationContext.getBeansOfType(ApiClient.class);
        apiClients.values().forEach(e -> API_CLIENT_MAP.putIfAbsent(e.getPlatform(), e));
    }

    public ApiClient getApiType(Platform platform) {
        return API_CLIENT_MAP.get(platform);
    }
}
