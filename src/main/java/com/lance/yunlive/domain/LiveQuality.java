package com.lance.yunlive.domain;

import lombok.Data;

/**
 * 直播画质选项
 */
@Data
public class LiveQuality {
    private String fd;
    private String ld;
    private String sd;
    private String hd;
    private String od;
    private String status;
}
