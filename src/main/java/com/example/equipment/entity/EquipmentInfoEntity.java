package com.example.equipment.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 设备类型
 */
@Data
@TableName("equipment_info")
public class EquipmentInfoEntity {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String type;
    private String subregionName;
    private String equipmentCode;
    private String ip;

    private String cpu;
    private String memory;
    private String disc;
    /*private String netStatus;*/
    /**
     *  -1 初始化状态 0-停止状态  1-运行状态 2-异常状态 3-废弃状态
     * */
    private Integer status;
    private String version;
    //CPU占用率
    private String cpuOccupancy;
    //内存占用率
    private String memoryOccupancy;
    //磁盘占用率
    private String discOccupancy;
    //网络状态
    private String netWorkStatus;
}
