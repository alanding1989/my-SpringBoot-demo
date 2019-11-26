package com.alanding.providerticket.controller;

import com.alanding.providerticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  @Author      :  AlanDing
 *  @Time        :  2019/11/21 下午7:24
 *  @File        :  TicketController.java
 *  @Description :
 */

@RestController
public class TicketController {

    @Autowired
    TicketService ticketService;

    /**
     * 对外的服务接口，就是url地址，跟单体应用差不多，不过这个接口企业内部使用， 不对外暴露。
     */
    @GetMapping("/ticket")
    public String getTicket() {
        return ticketService.getTicket();
    }
}
