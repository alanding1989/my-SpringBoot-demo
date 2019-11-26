package com.alanding.user.service;

import com.alanding.ticket.service.TicketService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

/**
 *  @Author      :  AlanDing
 *  @Time        :  2019/11/20 下午10:24
 *  @File        :  UserService.java
 *  @Description :
 */


@Service
public class UserService {

    // 从服务提供方引入的服务接口，包名必须和服务方一致
    @Reference
    private TicketService ticketService;

    public void hello() {
        String ticket = ticketService.getTicket();
        System.out.println("买到票了: " + ticket);
    }
}
