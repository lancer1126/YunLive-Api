package com.lance.yunlive.common.constrants;

public class ApiUrl {
    public static class Bilibili {
        public static final String RECOMMEND = "https://api.live.bilibili.com/room/v1/room/get_user_recommend?page=%s&page_size=%s";
        public static final String SINGLE_ROOM = "https://api.live.bilibili.com/xlive/web-room/v1/index/getH5InfoByRoom?room_id=%s";
        public static final String ROOM_INIT = "https://api.live.bilibili.com/room/v1/Room/room_init?id=%s";
        public static final String PLAY_URL = "https://api.live.bilibili.com/xlive/web-room/v1/playUrl/playUrl?"
                + "&platform=h5&https_url_req=1&ptype=16"
                + "&cid=%s"
                + "&qn=%s";
        public static final String SEARCH = "https://api.bilibili.com/x/web-interface/search/type?" +
                "context=&search_type=live_user&cover_type=user_cover" +
                "&page=1&order=&keyword=%s&category_id=&__refresh__=true" +
                "&_extra=&highlight=1&single_column=0";
        public static final String AREA = "https://api.live.bilibili.com/xlive/web-interface/v1/index/getWebAreaList?source_id=2";
        public static final String GROUP_AREA = "https://api.live.bilibili.com/xlive/web-interface/v1/second/getList?" +
                "platform=web" +
                "&parent_area_id=%s" +
                "&area_id=%s" +
                "&page=%s";
    }

    public static class Douyu {
        public static final String RECOMMEND = "https://m.douyu.com/api/room/list?page=%s&type=%s";
        public static final String ROOM_INFO = "http://open.douyucdn.cn/api/RoomApi/room/";
    }

    public static class Huya {
        public static final String RECOMMEND = "https://www.huya.com/cache.php?m=LiveList&do=getLiveListByPage&tagAll=0&page=%s";
    }
}
