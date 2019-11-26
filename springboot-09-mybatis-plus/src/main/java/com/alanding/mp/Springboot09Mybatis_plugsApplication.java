package com.alanding.mp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.alanding.mp.dao")
public class Springboot09Mybatis_plugsApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot09Mybatis_plugsApplication.class, args);
    }

}
