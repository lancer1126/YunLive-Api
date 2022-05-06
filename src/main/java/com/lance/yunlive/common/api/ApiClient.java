package com.lance.yunlive.common.api;

import com.lance.yunlive.domain.LiveRoom;

import java.util.List;

public abstract class ApiClient {
    public abstract List<LiveRoom> getRecommend(int page, int size);
}
