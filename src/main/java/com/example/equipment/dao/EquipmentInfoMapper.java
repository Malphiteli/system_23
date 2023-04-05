package com.example.equipment.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.equipment.entity.EquipmentInfoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EquipmentInfoMapper extends BaseMapper<EquipmentInfoEntity> {
    List<EquipmentInfoEntity> noSubregionList();
}
