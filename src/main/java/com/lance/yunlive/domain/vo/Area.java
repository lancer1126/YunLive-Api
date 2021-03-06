package com.lance.yunlive.domain.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Area {
    private String areaId;
    private String areaType;
    private String areaName;
    private String areaPic;
    private String shortName;
    private String groupId;
    private String groupName;
}
