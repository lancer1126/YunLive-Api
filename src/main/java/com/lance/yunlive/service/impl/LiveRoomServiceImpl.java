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
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Future;

@Service
@Slf4j
public class LiveRoomServiceImpl implements LiveRoomService {

    @Resource
    PlatformService platformService;

    @Override
    public List<LiveRoom> getRecommend(int page, int size) {
        List<LiveRoom> recList = new CopyOnWriteArrayList<>();
        List<Platform> platformList = new ArrayList<>(Arrays.asList(Platform.values()));
        List<Future<List<LiveRoom>>> futureResList = new CopyOnWriteArrayList<>();

        for (Platform platform : platformList) {
            try {
                Future<List<LiveRoom>> futureRes = platformService.getRecByPlatformAsync(platform.name, page, size);
                if (futureRes.get() != null) {
                    futureResList.add(futureRes);
                    log.info("平台：" + platform.name + " 获取到 " + futureRes.get().size() + "条数据");
                } else {
                    log.warn("平台：" + platform.name + " 内容为空");
                }
            } catch (Exception e) {
                log.error("获取平台推荐内容错误，平台：" + platform.name);
            }
        }

        for (Future<List<LiveRoom>> res : futureResList) {
            try {
                recList.addAll(res.get());
            } catch (Exception e) {
                log.error("读取异步推荐内容错误");
            }
        }
        log.info("=================================================");
        return recList;
    }

    public List<LiveRoom> getRecommendByPlatform(String platform, int page, int size) {
        return platformService.getRecByPlatform(platform, page, size);
    }

}
