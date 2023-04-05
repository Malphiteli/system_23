package com.example.equipment.vo;

import lombok.Data;

@Data
public class EquipmentListVo {
    private Long id;
    private String type;
    private String subregionName;
    private String equipmentCode;
    /**
     *  0-停止状态  1-运行状态
     * */
    private Integer status;
    private String statusStr;
    private String ip;

    private String version;
    private String cpu;
    private String memory;
    private String disc;


}
