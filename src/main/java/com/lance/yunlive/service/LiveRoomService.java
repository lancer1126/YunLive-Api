package com.lance.yunlive.service;

import com.lance.yunlive.domain.LiveRoom;

import java.util.List;

public interface LiveRoomService {
    List<LiveRoom> getRecommendContent(int page, int size);
}
