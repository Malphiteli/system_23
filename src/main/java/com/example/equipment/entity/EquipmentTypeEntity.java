package com.example.equipment.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 设备类型
 */
@Data
@TableName("equipment_type")
public class EquipmentTypeEntity {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String type;
    //描述
    private String description;
}
