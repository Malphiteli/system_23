package com.example.equipment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.equipment.dao.EquipmentInfoMapper;
import com.example.equipment.dao.EquipmentVersionMapper;
import com.example.equipment.dao.EquipmentWorkLogMapper;
import com.example.equipment.entity.EquipmentInfoEntity;
import com.example.equipment.entity.EquipmentVersionEntity;
import com.example.equipment.entity.EquipmentWorkLogEntity;
import com.example.equipment.service.IEquipmentInfoService;
import com.example.equipment.utils.UUIDUtil;
import com.example.equipment.vo.EquipmentListVo;
import com.example.equipment.vo.EquipmentMaintenanceVo;
import com.example.equipment.vo.XResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class EquipmentInfoServiceImpl extends ServiceImpl<EquipmentInfoMapper, EquipmentInfoEntity> implements IEquipmentInfoService {
    @Autowired
    private EquipmentVersionMapper equipmentVersionMapper;

    @Autowired
    private EquipmentInfoMapper equipmentInfoMapper;

    @Autowired
    private EquipmentWorkLogMapper equipmentWorkLogMapper;

    @Override
    public XResult addInfo(EquipmentInfoEntity entity) {
        XResult result = new XResult();
        result.setCode(true);
        entity.setStatus(-1);
        EquipmentVersionEntity byType = equipmentVersionMapper.findByType(entity.getType());
        if (ObjectUtils.isEmpty(byType)) {
            entity.setVersion("1.0");
        } else {
            entity.setVersion(byType.getVersion());
        }
        entity.setEquipmentCode(UUIDUtil.generateShortUuid());
        this.save(entity);
        result.setMessage("新增设备成功");
        return result;
    }

    @Override
    public XResult deleteInfo(Long id) {
        XResult result = new XResult();
        result.setCode(true);
        this.removeById(id);
        result.setMessage("删除设备成功");
        return result;
    }

    @Override
    public XResult updateInfo(EquipmentInfoEntity entity) {
        XResult result = new XResult();
        result.setCode(true);
        this.updateById(entity);
        result.setMessage("更新设备成功");
        return result;
    }

    @Override
    public XResult lists(EquipmentInfoEntity entity) {
        XResult result = new XResult();
        result.setCode(true);
        Map map = new HashMap<>();
        if (!ObjectUtils.isEmpty(entity.getType())) {
            map.put("type", entity.getType());
        }
        if (!ObjectUtils.isEmpty(entity.getSubregionName())) {
            map.put("subregion_name", entity.getSubregionName());
        }
        List<EquipmentInfoEntity> list = this.listByMap(map);
        List<EquipmentListVo> res = new ArrayList<>();
        for (EquipmentInfoEntity entity1 : list) {
            EquipmentListVo vo = new EquipmentListVo();
            vo.setId(entity1.getId());
            vo.setType(entity1.getType());
            vo.setVersion(entity1.getVersion());
            vo.setEquipmentCode(entity1.getEquipmentCode());
            vo.setStatus(entity1.getStatus());
            if (entity1.getStatus() == 0) {
                vo.setStatusStr("停止状态");
            } else if (entity1.getStatus() == 1) {
                vo.setStatusStr("运行中");
            } else if (entity1.getStatus() == -1) {
                vo.setStatusStr("初始化状态");
            } else if (entity1.getStatus() == 3) {
                vo.setStatusStr("废弃状态");
            } else {
                vo.setStatusStr("异常状态");
            }
            vo.setCpu(entity1.getCpu());
            vo.setMemory(entity1.getMemory());
            vo.setDisc(entity1.getDisc());
            vo.setSubregionName(entity1.getSubregionName());
            vo.setIp(entity1.getIp());
            res.add(vo);
        }
        result.setMessage(res);
        return result;
    }

    @Override
    public XResult maintenance(EquipmentInfoEntity equipmentInfoEntity) {
        XResult result = new XResult();
        result.setCode(true);
        Map map = new HashMap<>();
        if (!ObjectUtils.isEmpty(equipmentInfoEntity.getType())) {
            map.put("type", equipmentInfoEntity.getType());
        }
        if (!ObjectUtils.isEmpty(equipmentInfoEntity.getSubregionName())) {
            map.put("subregion_name", equipmentInfoEntity.getSubregionName());
        }
        if (!ObjectUtils.isEmpty(equipmentInfoEntity.getStatus())) {
            map.put("status", equipmentInfoEntity.getStatus());
        }
        List<EquipmentInfoEntity> list = this.listByMap(map);
        List<EquipmentMaintenanceVo> res = new ArrayList<>();
        for (EquipmentInfoEntity entity : list) {
            EquipmentMaintenanceVo vo = new EquipmentMaintenanceVo();
            vo.setId(entity.getId());
            vo.setType(entity.getType());
            vo.setVersion(entity.getVersion());
            vo.setEquipmentCode(entity.getEquipmentCode());
            vo.setStatus(entity.getStatus());
            if (entity.getStatus() == 0) {
                vo.setStatusStr("停止状态");
            } else if (entity.getStatus() == 1) {
                vo.setStatusStr("运行中");
            } else if (entity.getStatus() == -1) {
                vo.setStatusStr("初始化状态");
            } else if (entity.getStatus() == 3) {
                vo.setStatusStr("废弃状态");
            } else {
                vo.setStatusStr("异常状态");
            }
            vo.setCpuOccupancy(entity.getCpuOccupancy());
            vo.setMemoryOccupancy(entity.getMemoryOccupancy());
            vo.setNetWorkStatus(entity.getNetWorkStatus());
            vo.setDiscOccupancy(entity.getDiscOccupancy());
            vo.setSubregionName(entity.getSubregionName());
            vo.setFlag(false);
            EquipmentVersionEntity byType = equipmentVersionMapper.findByType(entity.getType());
            if (!ObjectUtils.isEmpty(byType) && !entity.getVersion().equals(byType.getVersion())) {
                vo.setFlag(true);
            }
            res.add(vo);
        }
        result.setMessage(res);
        return result;
    }

    @Override
    public XResult changeStatus(Long id, Integer status) {
        XResult result = new XResult();
        result.setCode(true);
        EquipmentInfoEntity byId = this.getById(id);
        byId.setStatus(status);
        this.updateById(byId);
        if (status == 0) {
            EquipmentWorkLogEntity work = new EquipmentWorkLogEntity();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String transformDate = simpleDateFormat.format(new Date());
            work.setWorkLog("正在关机-" + transformDate);
            work.setEquipmentCode(byId.getEquipmentCode());
            equipmentWorkLogMapper.insert(work);
            result.setMessage("关机成功");
        }
        if (status == 1) {
            EquipmentWorkLogEntity work = new EquipmentWorkLogEntity();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String transformDate = simpleDateFormat.format(new Date());
            work.setWorkLog("正在重启-" + transformDate);
            work.setEquipmentCode(byId.getEquipmentCode());
            equipmentWorkLogMapper.insert(work);
            result.setMessage("重启成功");
        }
        return result;
    }

    @Override
    public XResult versionUpgrade(Long id) {
        XResult result = new XResult();
        result.setCode(true);
        EquipmentInfoEntity byId = this.getById(id);
        EquipmentVersionEntity byType = equipmentVersionMapper.findByType(byId.getType());
        byId.setVersion(byType.getVersion());
        this.updateById(byId);
        result.setMessage("版本升级成功");
        return result;
    }

    @Override
    public XResult noSubregionList() {
        XResult result = new XResult();
        result.setCode(true);
        List<EquipmentInfoEntity> equipmentInfoEntities = equipmentInfoMapper.noSubregionList();
        result.setMessage(equipmentInfoEntities);
        return result;
    }

    @Override
    public XResult addSubregion(String subregionName, String equipmentCode) {
        XResult result = new XResult();
        result.setCode(true);
        Map map = new HashMap<>();
        if (!ObjectUtils.isEmpty(equipmentCode)) {
            map.put("equipment_code", equipmentCode);
        }
        EquipmentInfoEntity entity = (EquipmentInfoEntity) this.listByMap(map).get(0);
        entity.setSubregionName(subregionName);
        this.updateById(entity);
        result.setMessage("添加分区成功");
        return result;
    }

    @Override
    public XResult subregionEquipmentList(String subregionName, String type) {
        XResult result = new XResult();
        result.setCode(true);
        Map map = new HashMap<>();
        map.put("subregion_name", subregionName);
        map.put("status", -1);
        map.put("type", type);
        List<EquipmentInfoEntity> list = this.listByMap(map);
        result.setMessage(list);
        return result;
    }

    @Override
    public XResult replace(String equipmentCode, String replaceEquipment) {
        Map map = new HashMap<>();
        XResult result = new XResult();
        result.setCode(true);
        if (!ObjectUtils.isEmpty(equipmentCode)) {
            map.put("equipment_code", equipmentCode);
        }
        EquipmentInfoEntity entity = (EquipmentInfoEntity) this.listByMap(map).get(0);
        entity.setSubregionName("");
        //设备改成废弃
        entity.setStatus(3);
        this.updateById(entity);

        Map map1 = new HashMap<>();
        if (!ObjectUtils.isEmpty(replaceEquipment)) {
            map1.put("equipment_code", replaceEquipment);
        }
        EquipmentInfoEntity replaceEntity = (EquipmentInfoEntity) this.listByMap(map1).get(0);
        replaceEntity.setStatus(1);
        this.updateById(replaceEntity);
        result.setMessage("替换成功");
        return result;
    }
}
