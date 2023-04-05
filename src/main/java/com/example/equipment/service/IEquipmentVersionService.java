package com.example.equipment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.equipment.entity.EquipmentVersionEntity;
import com.example.equipment.vo.XResult;

public interface IEquipmentVersionService extends IService<EquipmentVersionEntity> {
    XResult addInfo(EquipmentVersionEntity entity);
    XResult deleteInfo(Long id);
    XResult updateInfo(EquipmentVersionEntity entity);
    XResult lists(EquipmentVersionEntity entity);
}
