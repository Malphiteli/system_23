package com.example.equipment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.equipment.entity.EquipmentTypeEntity;
import com.example.equipment.entity.EquipmentWorkLogEntity;
import com.example.equipment.vo.XResult;

public interface IEquipmentWorkLogService extends IService<EquipmentWorkLogEntity> {
    XResult addInfo(EquipmentWorkLogEntity entity);
    XResult lists(EquipmentWorkLogEntity entity);
}
