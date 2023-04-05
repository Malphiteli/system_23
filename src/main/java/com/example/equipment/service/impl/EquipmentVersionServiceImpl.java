package com.example.equipment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.equipment.dao.EquipmentVersionMapper;
import com.example.equipment.entity.EquipmentTypeEntity;
import com.example.equipment.entity.EquipmentVersionEntity;
import com.example.equipment.service.IEquipmentVersionService;
import com.example.equipment.vo.XResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentVersionServiceImpl extends ServiceImpl<EquipmentVersionMapper, EquipmentVersionEntity> implements IEquipmentVersionService {

    @Override
    public XResult addInfo(EquipmentVersionEntity entity) {
        XResult result = new XResult();
        result.setCode(true);
        result.setMessage("新增版本成功");
        this.save(entity);
        return result;
    }

    @Override
    public XResult deleteInfo(Long id) {
        XResult result = new XResult();
        result.setCode(true);
        this.removeById(id);
        result.setMessage("删除版本成功");
        return result;
    }

    @Override
    public XResult updateInfo(EquipmentVersionEntity entity) {
        XResult result = new XResult();
        result.setCode(true);
        this.updateById(entity);
        result.setMessage("更新版本成功");
        return result;
    }

    @Override
    public XResult lists(EquipmentVersionEntity entity) {
        XResult result = new XResult();
        result.setCode(true);
        List<EquipmentVersionEntity> list = this.list();
        result.setMessage(list);
        return result;
    }
}
