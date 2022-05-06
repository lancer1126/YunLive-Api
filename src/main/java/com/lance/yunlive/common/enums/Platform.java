package com.lance.yunlive.common.enums;

public enum Platform {
    BILIBILI("bilibili"),
    DOUYU("douyu"),
    HUYA("huya"),
    CC("cc"),
    EGAME("egame");

    public String name;
    Platform(String name){
        this.name = name;
    }
}
