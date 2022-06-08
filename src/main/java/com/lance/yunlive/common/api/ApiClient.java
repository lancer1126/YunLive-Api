package com.lance.yunlive.common.api;

import com.lance.yunlive.common.enums.Platform;
import com.lance.yunlive.domain.vo.*;

import java.util.List;

public interface ApiClient {

    /**
     * 判断实现类的平台
     */
    Platform getPlatform();

    /**
     * 获取String类型的平台名
     */
    String getPlatformName();

    /**
     * 获取推荐内容
     */
    List<LiveRoom> getRecommend(int page, int size);

    /**
     * 根据roomId获取单个房间信息
     */
    LiveRoom getSingleRoomInfo(String roomId);

    /**
     * 根据roomId获取直播间每个画质的真是地址
     */
    LiveQuality getRealUrl(String roomId);

    /**
     * 根据关键字查询主播
     */
    List<Streamer> search(String keyWord);

    /**
     * 获取分类列表
     */
    List<AreaGroup> getAreas();
}
