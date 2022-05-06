package com.lance.yunlive.common.api;

import com.lance.yunlive.common.enums.Platform;
import com.lance.yunlive.domain.LiveRoom;

import java.util.List;

public interface ApiClient {

    Platform getPlatform();

    List<LiveRoom> getRecommend(int page, int size);
}
