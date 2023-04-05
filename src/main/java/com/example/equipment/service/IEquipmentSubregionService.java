package com.example.equipment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.equipment.entity.EquipmentSubregionEntity;
import com.example.equipment.entity.EquipmentTypeEntity;
import com.example.equipment.vo.XResult;

public interface IEquipmentSubregionService extends IService<EquipmentSubregionEntity> {
    XResult addInfo(EquipmentSubregionEntity entity);
    XResult deleteInfo(Long id);
    XResult updateInfo(EquipmentSubregionEntity entity);
    XResult lists(EquipmentSubregionEntity entity);
}
