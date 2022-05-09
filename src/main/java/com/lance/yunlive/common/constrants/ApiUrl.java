package com.lance.yunlive.common.constrants;

public class ApiUrl {
    public static class Bilibili {
        public static final String RECOMMEND = "https://api.live.bilibili.com/room/v1/room/get_user_recommend?page=%s&page_size=%s";
        public static final String SINGLE_ROOM = "https://api.live.bilibili.com/xlive/web-room/v1/index/getH5InfoByRoom?room_id=%s";
    }

    public static class Douyu {
        public static final String RECOMMEND = "https://m.douyu.com/api/room/list?page=%s&type=%s";
    }

    public static class Huya {
        public static final String RECOMMEND = "https://www.huya.com/cache.php?m=LiveList&do=getLiveListByPage&tagAll=0&page=%s";
    }
}
