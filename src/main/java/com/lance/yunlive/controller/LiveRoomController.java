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
    public ResultEntity getRecommendContent(@RequestParam("page") int page, @RequestParam("size") int size) {
        List<LiveRoom> recommendContent = liveRoomService.getRecommendContent(page, size);
        return ResultEntity.success(recommendContent);
    }

}
