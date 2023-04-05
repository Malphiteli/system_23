package com.example.equipment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.equipment.dao.EquipmentSubregionMapper;
import com.example.equipment.dao.EquipmentTypeMapper;
import com.example.equipment.entity.EquipmentSubregionEntity;
import com.example.equipment.entity.EquipmentTypeEntity;
import com.example.equipment.service.IEquipmentSubregionService;
import com.example.equipment.service.IEquipmentTypeService;
import com.example.equipment.vo.XResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentSubregionServiceImpl extends ServiceImpl<EquipmentSubregionMapper, EquipmentSubregionEntity> implements IEquipmentSubregionService {

    @Override
    public XResult addInfo(EquipmentSubregionEntity entity) {
        XResult result = new XResult();
        result.setCode(true);
        result.setMessage("新增分区成功");
        this.save(entity);
        return result;
    }

    @Override
    public XResult deleteInfo(Long id) {
        XResult result = new XResult();
        result.setCode(true);
        this.removeById(id);
        result.setMessage("删除分区成功");
        return result;
    }

    @Override
    public XResult updateInfo(EquipmentSubregionEntity entity) {
        XResult result = new XResult();
        result.setCode(true);
        this.updateById(entity);
        result.setMessage("更新分区成功");
        return result;
    }

    @Override
    public XResult lists(EquipmentSubregionEntity entity) {
        XResult result = new XResult();
        result.setCode(true);
        List<EquipmentSubregionEntity> list = this.list();
        result.setMessage(list);
        return result;
    }
}
