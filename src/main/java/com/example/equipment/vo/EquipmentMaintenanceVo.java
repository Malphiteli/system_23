package com.example.equipment.vo;

import lombok.Data;

@Data
public class EquipmentMaintenanceVo {
    private Long id;
    private String type;
    private String subregionName;
    private String equipmentCode;
    /**
     *  0-停止状态  1-运行状态
     * */
    private Integer status;
    private String statusStr;
    private String version;
    private Boolean flag;
    //CPU占用率
    private String cpuOccupancy;
    //内存占用率
    private String memoryOccupancy;
    //磁盘占用率
    private String discOccupancy;
    //网络状态
    private String netWorkStatus;
}
