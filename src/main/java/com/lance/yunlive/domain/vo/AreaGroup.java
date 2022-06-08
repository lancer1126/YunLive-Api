package com.lance.yunlive.domain.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class AreaGroup {
    private String groupId;
    private String groupName;
    private String platform;
    private List<Area> areaList;
}
