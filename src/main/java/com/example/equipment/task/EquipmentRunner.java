package com.example.equipment.task;

import com.example.equipment.dao.EquipmentInfoMapper;
import com.example.equipment.dao.EquipmentWorkLogMapper;
import com.example.equipment.entity.EquipmentInfoEntity;
import com.example.equipment.entity.EquipmentWorkLogEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
@Slf4j
public class EquipmentRunner implements CommandLineRunner {
    @Autowired
    private EquipmentInfoMapper equipmentInfoMapper;
    @Autowired
    private EquipmentWorkLogMapper equipmentWorkLogMapper;

    @Override
    public void run(String... args) throws Exception {
        log.info("项目启动后运行");
        while (true) {
            List<EquipmentInfoEntity> equipmentInfoEntities = equipmentInfoMapper.selectByMap(null);
            for (EquipmentInfoEntity entity : equipmentInfoEntities) {
                if (entity.getStatus() == 1) {
                    EquipmentWorkLogEntity work = new EquipmentWorkLogEntity();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    String transformDate = simpleDateFormat.format(new Date());
                    String log = "设备编号：" + entity.getEquipmentCode() + "正在运行," + transformDate;
                    work.setWorkLog(log);
                    work.setEquipmentCode(entity.getEquipmentCode());
                    int cpuOccupancy = (int) (Math.random() * 100);
                    work.setCpuOccupancy(String.valueOf(cpuOccupancy));
                    int smemoryOccupancy = (int) (Math.random() * 10);
                    work.setMemoryOccupancy(String.valueOf(smemoryOccupancy + 60));
                    int discOccupancy = (int) (Math.random() * 10);
                    work.setDiscOccupancy(String.valueOf(discOccupancy + 30));
                    int netWork = (int) (Math.random() * 100);
                    if (netWork < 70) {
                        entity.setNetWorkStatus("良好");
                        work.setNetWorkStatus("良好");
                    } else if (netWork >= 70 && netWork < 90) {
                        entity.setNetWorkStatus("差");
                        work.setNetWorkStatus("差");
                    } else {
                        entity.setNetWorkStatus("离线");
                        work.setNetWorkStatus("离线");
                    }
                    int stop = (int) (Math.random() * 100);
                    if (stop < 3) {
                        entity.setStatus(2);
                        log = "设备编号：" + entity.getEquipmentCode() + "设备故障," + transformDate;
                        work.setWorkLog(log);
                    }
                    equipmentWorkLogMapper.insert(work);
                    entity.setCpuOccupancy(String.valueOf(cpuOccupancy));
                    entity.setMemoryOccupancy(String.valueOf(smemoryOccupancy + 60));
                    entity.setDiscOccupancy(String.valueOf(discOccupancy + 30));
                    equipmentInfoMapper.updateById(entity);
                }
            }
            Thread.sleep(30000);
        }
    }
}
