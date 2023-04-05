package com.example.equipment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.equipment.dao.EquipmentTypeMapper;
import com.example.equipment.dao.EquipmentWorkLogMapper;
import com.example.equipment.entity.EquipmentTypeEntity;
import com.example.equipment.entity.EquipmentWorkLogEntity;
import com.example.equipment.service.IEquipmentTypeService;
import com.example.equipment.service.IEquipmentWorkLogService;
import com.example.equipment.vo.XResult;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EquipmentWorkLogServiceImpl extends ServiceImpl<EquipmentWorkLogMapper , EquipmentWorkLogEntity> implements IEquipmentWorkLogService {

    @Override
    public XResult addInfo(EquipmentWorkLogEntity entity) {
        XResult result = new XResult();
        result.setCode(true);
        return result;
    }

    @Override
    public XResult lists(EquipmentWorkLogEntity entity) {
        XResult result = new XResult();
        result.setCode(true);
        Map map = new HashMap<>();
        if(!ObjectUtils.isEmpty(entity.getEquipmentCode())){
            map.put("equipment_code",entity.getEquipmentCode());
        }
        List list = this.listByMap(map);
        result.setMessage(list);
        return result;
    }
}
