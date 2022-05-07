package com.lance.yunlive.controller;

import com.lance.yunlive.common.ResultEntity;
import com.lance.yunlive.domain.LiveRoom;
import com.lance.yunlive.service.LiveRoomService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/live")
public class LiveRoomController {

    @Resource
    LiveRoomService liveRoomService;

    @GetMapping("/rec")
    public ResultEntity getRecommendContent(@RequestParam(value = "page", defaultValue = "1") int page,
                                            @RequestParam(value = "size", defaultValue = "10") int size) {
        List<LiveRoom> recommendContent = liveRoomService.getRecommend(page, size);
        return ResultEntity.success(recommendContent);
    }

    @GetMapping("/recByPlatform")
    public ResultEntity getRecByPlatform(@RequestParam("pl") String platform,
                                         @RequestParam(value = "page", defaultValue = "1") int page,
                                         @RequestParam(value = "size", defaultValue = "10") int size) {
        List<LiveRoom> liveRoomList = liveRoomService.getRecommendByPlatform(platform, page, size);
        return ResultEntity.success(liveRoomList);
    }

}
