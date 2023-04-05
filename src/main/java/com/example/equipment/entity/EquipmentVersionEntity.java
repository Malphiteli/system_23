package com.example.equipment.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 设备类型
 */
@Data
@TableName("equipment_version")
public class EquipmentVersionEntity {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String type;
    private String version;
    private String description;
}
