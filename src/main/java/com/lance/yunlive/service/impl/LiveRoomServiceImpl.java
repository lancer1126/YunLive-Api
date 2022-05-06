package com.lance.yunlive.service.impl;

import com.lance.yunlive.common.enums.Platform;
import com.lance.yunlive.domain.LiveRoom;
import com.lance.yunlive.service.LiveRoomService;
import com.lance.yunlive.service.PlatformService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;

@Service
@Slf4j
public class LiveRoomServiceImpl implements LiveRoomService {

    @Resource
    PlatformService platformService;

    @Override
    public List<LiveRoom> getRecommendContent(int page, int size) {
        List<LiveRoom> recList = Collections.synchronizedList(new ArrayList<>());
        List<Platform> platformList = new ArrayList<>(Arrays.asList(Platform.values()));

        for (Platform platform : platformList) {
            try {
                Future<List<LiveRoom>> futureRes = platformService.getRecByContentAsync(platform.name, page, size);
                if (futureRes.get() != null) {
                    recList.addAll(futureRes.get());
                } else {
                    log.warn("平台：" + platform.name + " 内容为空");
                }
            } catch (Exception e) {
                log.error("获取平台推荐内容错误，平台：" + platform.name);
            }
        }
        return recList;
    }

    public List<LiveRoom> getRecommendByPlatform(String platform, int page, int size) {
        return null;
    }

}
