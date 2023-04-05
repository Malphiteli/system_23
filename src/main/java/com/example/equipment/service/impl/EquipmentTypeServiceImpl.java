package com.example.equipment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.equipment.dao.EquipmentTypeMapper;
import com.example.equipment.entity.EquipmentTypeEntity;
import com.example.equipment.service.IEquipmentTypeService;
import com.example.equipment.vo.XResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentTypeServiceImpl extends ServiceImpl<EquipmentTypeMapper, EquipmentTypeEntity> implements IEquipmentTypeService {

    @Override
    public XResult addInfo(EquipmentTypeEntity entity) {
        XResult result = new XResult();
        result.setCode(true);
        result.setMessage("新增类型成功");
        this.save(entity);
        return result;
    }

    @Override
    public XResult deleteInfo(Long id) {
        XResult result = new XResult();
        result.setCode(true);
        this.removeById(id);
        result.setMessage("删除类型成功");
        return result;
    }

    @Override
    public XResult updateInfo(EquipmentTypeEntity entity) {
        XResult result = new XResult();
        result.setCode(true);
        this.updateById(entity);
        result.setMessage("更新类型成功");
        return result;
    }

    @Override
    public XResult lists(EquipmentTypeEntity entity) {
        XResult result = new XResult();
        result.setCode(true);
        List<EquipmentTypeEntity> list = this.list();
        result.setMessage(list);
        return result;
    }
}
