package com.lance.yunlive.domain;

import lombok.Data;

@Data
public class Streamer {
    private String platform;
    private String nickName;
    private String roomId;
    private String headPic;
    private String cateName;
    private String isLive;
    private Integer followers;
    private Integer isFollowed;
}
