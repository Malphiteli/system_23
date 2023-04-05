package com.example.equipment.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 设备分区
 */
@Data
@TableName("equipment_subregion")
public class EquipmentSubregionEntity {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String subregionName;
    private String description;
    //地址
    private String address;
}
