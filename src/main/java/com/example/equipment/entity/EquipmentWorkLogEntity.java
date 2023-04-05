package com.example.equipment.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 设备类型
 */
@Data
@TableName("equipment_work_log")
public class EquipmentWorkLogEntity {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String equipmentCode;
    private String workLog;
    //CPU占用率
    private String cpuOccupancy;
    //内存占用率
    private String memoryOccupancy;
    //磁盘占用率
    private String discOccupancy;
    //网络状态
    private String netWorkStatus;

}
