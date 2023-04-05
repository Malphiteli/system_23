package com.example.equipment;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.equipment.dao")
public class AnalogEquipmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnalogEquipmentApplication.class, args);
    }

}
