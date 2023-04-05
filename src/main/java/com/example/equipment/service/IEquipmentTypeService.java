package com.example.equipment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.equipment.entity.EquipmentTypeEntity;
import com.example.equipment.vo.XResult;

public interface IEquipmentTypeService extends IService<EquipmentTypeEntity> {
    XResult addInfo(EquipmentTypeEntity entity);
    XResult deleteInfo(Long id);
    XResult updateInfo(EquipmentTypeEntity entity);
    XResult lists(EquipmentTypeEntity entity);
}
