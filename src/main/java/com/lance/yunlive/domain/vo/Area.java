package com.lance.yunlive.domain.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class Area {
    private String platform;
    private String areaType;
    private String typeName;
    private String areaId;
    private String areaName;
    private String areaPic;
    private String shortName;
}
