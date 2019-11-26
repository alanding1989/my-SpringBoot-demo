package com.alanding.ticket;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 1、将服务提供者注册到注册中心
 *      １、引入dubbo和zookeeper相关依赖
 *      ２、配置dubbo的扫描包和注册中心地址
 *      ３、使用@Service发布服务
 */

@EnableDubbo(scanBasePackages = "com.alanding.ticket.service")
@SpringBootApplication
public class ProviderTicketApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderTicketApplication.class, args);
    }
}
