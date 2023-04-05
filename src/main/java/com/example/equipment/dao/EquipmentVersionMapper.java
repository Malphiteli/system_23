package com.example.equipment.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.equipment.entity.EquipmentVersionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface EquipmentVersionMapper extends BaseMapper<EquipmentVersionEntity> {
    EquipmentVersionEntity findByType(@Param("type") String type);
}
