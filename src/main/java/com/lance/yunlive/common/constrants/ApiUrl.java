package com.lance.yunlive.common.constrants;

public class ApiUrl {
    public static class Bilibili {
        public static final String RECOMMEND = "https://api.live.bilibili.com/room/v1/room/get_user_recommend?page=%s&page_size=%s";
        public static final String SINGLE_ROOM = "https://api.live.bilibili.com/xlive/web-room/v1/index/getH5InfoByRoom?room_id=%s";
    }
}
