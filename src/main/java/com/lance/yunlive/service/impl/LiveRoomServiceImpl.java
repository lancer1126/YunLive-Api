package com.lance.yunlive.service.impl;

import com.lance.yunlive.common.enums.Platform;
import com.lance.yunlive.domain.vo.Area;
import com.lance.yunlive.domain.vo.LiveQuality;
import com.lance.yunlive.domain.vo.LiveRoom;
import com.lance.yunlive.domain.vo.Streamer;
import com.lance.yunlive.service.LiveRoomService;
import com.lance.yunlive.service.PlatformService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LiveRoomServiceImpl implements LiveRoomService {

    @Resource
    PlatformService platformService;

    @Override
    public List<LiveRoom> getRecommend(int page, int size) {
        List<LiveRoom> recList = new ArrayList<>();
        List<Platform> platformList = new ArrayList<>(Arrays.asList(Platform.values()));

        // 调用异步方法获取推荐内容
        List<CompletableFuture<List<LiveRoom>>> futures = platformList.stream()
                .map(p -> platformService.getRecByPlatformAsync(p.name, page, size))
                .collect(Collectors.toList());
        // 等待线程执行完毕
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        // 获取内容
        for (CompletableFuture<List<LiveRoom>> res : futures) {
            try {
                if (res.get() != null) {
                    recList.addAll(res.get());
                }
            } catch (Exception e) {
                log.error("读取异步推荐内容出错");
            }
        }
        log.info("=================================================");
        Collections.shuffle(recList);
        return recList;
    }

    public List<LiveRoom> getRecommendByPlatform(String platform, int page, int size) {
        return platformService.getRecByPlatform(platform, page, size);
    }

    @Override
    public LiveRoom getRoomInfo(String uid, String platform, String roomId) {
        return platformService.getRoomInfo(uid, platform, roomId);
    }

    @Override
    public LiveQuality getRealUrl(String platform, String roomId) {
        return platformService.getRealUrl(platform, roomId);
    }

    @Override
    public List<Streamer> search(String platform, String keyWord) {
        List<Streamer> streamerList = new ArrayList<>();
        if ("all".equals(platform) || "".equals(platform)) {
            // 查询所有平台，异步多线程查询
            List<Platform> platformList = new ArrayList<>(Arrays.asList(Platform.values()));
            List<CompletableFuture<List<Streamer>>> futures = platformList.stream()
                    .map(p -> platformService.searchAsync(p.name, keyWord))
                    .collect(Collectors.toList());
            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

            // 所有线程都执行完毕后聚合数据
            for (CompletableFuture<List<Streamer>> res : futures) {
                try {
                    if (res.get() != null) {
                        streamerList.addAll(res.get());
                    }
                } catch (Exception e) {
                    log.error("聚合搜索查询结果错误");
                }
            }
        } else {
            // 在指定平台进行查询
            streamerList.addAll(platformService.search(platform, keyWord));
        }
        return streamerList;
    }

    @Override
    public List<Area> getAreaByPlatform(String platform) {
        return platformService.getAreas(platform);
    }
}
