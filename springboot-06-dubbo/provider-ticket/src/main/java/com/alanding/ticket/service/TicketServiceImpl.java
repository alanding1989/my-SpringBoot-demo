package com.alanding.ticket.service;

import org.apache.dubbo.config.annotation.Service;

/**
 *  @Author      :  AlanDing
 *  @Time        :  2019/11/20 下午10:21
 *  @File        :  TicketServiceImpl.java
 *  @Description :
 */


@Service
public class TicketServiceImpl implements TicketService {
    @Override
    public String getTicket() {
        return "《厉害了，我的国》";
    }
}
