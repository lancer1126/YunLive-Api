package com.lance.yunlive.domain.vo;

import lombok.Data;

@Data
public class LiveRoom {
    private String platform;
    private String roomId;
    private String categoryId;
    private String categoryName;
    private String roomName;
    private String ownerName;
    private String roomPic;
    private String ownerHeadPic;
    private String realUrl;
    private int online;  //在线人数
    private int isLive;
    private int isFollowed; //是否关注此房间
    private String eGameToken;
}
