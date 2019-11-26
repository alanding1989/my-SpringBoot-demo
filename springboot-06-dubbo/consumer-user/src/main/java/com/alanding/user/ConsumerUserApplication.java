package com.alanding.user;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;




/**
 * １、配置注册中心，服务端口等属性
 * ２、将服务提供者的服务接口引入，注意保持全类名相同
 * ３、自动注入远端调用服务接口，编写消费逻辑
 * ４、配置dubbo包扫描和需要spring管理的Bean包扫描或者个别类扫描
 */
@EnableDubbo(scanBasePackages = "com.alanding.user.service")
@ComponentScan(value = {"com.alanding.user.service"})
@SpringBootApplication
public class ConsumerUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerUserApplication.class, args);
    }
}
