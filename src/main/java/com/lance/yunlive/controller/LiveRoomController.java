package com.lance.yunlive.controller;

import com.lance.yunlive.common.ResultEntity;
import com.lance.yunlive.domain.LiveRoom;
import com.lance.yunlive.service.LiveRoomService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/live")
public class LiveRoomController {

    @Resource
    LiveRoomService liveRoomService;

    @GetMapping("/rec")
    public ResultEntity getRecommendContent(@RequestParam(value = "page", defaultValue = "1") int page,
                                            @RequestParam(value = "size", defaultValue = "10") int size) {
        return ResultEntity.success(liveRoomService.getRecommend(page, size));
    }

    @GetMapping("/recByPlatform")
    public ResultEntity getRecByPlatform(@RequestParam("pl") String platform,
                                         @RequestParam(value = "page", defaultValue = "1") int page,
                                         @RequestParam(value = "size", defaultValue = "10") int size) {
        return ResultEntity.success(liveRoomService.getRecommendByPlatform(platform, page, size));
    }

    @GetMapping("/roomInfo")
    public ResultEntity getRoomInfo(@RequestParam("uid") String uid,
                                    @RequestParam("p") String platform,
                                    @RequestParam("rid") String roomId) {
        LiveRoom roomInfo = liveRoomService.getRoomInfo(uid, platform, roomId);
        if (roomInfo == null) {
            return ResultEntity.fail("获取房间信息失败");
        }
        return ResultEntity.success(roomInfo);
    }

    @GetMapping("/realUrl")
    public ResultEntity getRealUrl(@RequestParam("p") String platform,
                                   @RequestParam("rid") String roomId) {
        return ResultEntity.success(liveRoomService.getRealUrl(platform, roomId));
    }

    @GetMapping("/search")
    public ResultEntity search(@RequestParam("p") String platform,
                               @RequestParam("kw") String keyWord) {
        return ResultEntity.success(liveRoomService.search(platform, keyWord));
    }
}
