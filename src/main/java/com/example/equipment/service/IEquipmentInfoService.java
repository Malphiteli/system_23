package com.example.equipment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.equipment.entity.EquipmentInfoEntity;
import com.example.equipment.vo.XResult;

public interface IEquipmentInfoService extends IService<EquipmentInfoEntity> {
    XResult addInfo(EquipmentInfoEntity entity);
    XResult deleteInfo(Long id);
    XResult updateInfo(EquipmentInfoEntity entity);
    XResult lists(EquipmentInfoEntity entity);
    XResult maintenance(EquipmentInfoEntity equipmentInfoEntity);
    XResult changeStatus(Long id,Integer status);
    XResult versionUpgrade(Long id);
    XResult noSubregionList();
    XResult addSubregion(String subregionName,String equipmentCode);
    XResult subregionEquipmentList(String subregionName,String type);
    XResult replace(String equipmentCode,String replaceEquipment);

}
