package com.alanding.providerticket.service;

import org.springframework.stereotype.Service;

/**
 *  @Author      :  AlanDing
 *  @Time        :  2019/11/21 下午7:23
 *  @File        :  TicketService.java
 *  @Description :
 */

@Service
public class TicketService {
    public String getTicket() {
        System.out.println("8001");
        return "《厉害了，我的国》";
    }
}
