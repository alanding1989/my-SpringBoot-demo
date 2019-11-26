package com.alanding.consumeruser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *  @Author      :  AlanDing
 *  @Time        :  2019/11/21 下午8:20
 *  @File        :  UserController.java
 *  @Description :
 */

@RestController
public class UserController {

    // 注入一个http客户端，可以发送http请求，spring里有基于Reactive风格的
    // WebClient，RestTemplate将会被废弃。
    @Autowired
    private RestTemplate restTemplate;

    // @Autowired
    // public void setRestTemplate(RestTemplate restTemplate) {
    //     this.restTemplate = restTemplate;
    // }

    @GetMapping("/buy")
    public String buyTicket(String name) {
        // 获得服务提供者调用结果，
        // 方法参数：　url地址，http://服务提供者设置的应用名/服务接口地址
        //           服务提供者该服务服务方法的返回值类型
        String s = restTemplate.getForObject("http://provider-ticket/ticket", String.class);
        return name + "购买了" + s;
    }
}
