package com.lance.yunlive.common.enums;

public enum BiliQuality {
    FD("80"),
    LD("150"),
    SD("250"),
    HD("400"),
    OD("10000");
    public final String rate;
    BiliQuality(String rate) {
        this.rate = rate;
    }
}
