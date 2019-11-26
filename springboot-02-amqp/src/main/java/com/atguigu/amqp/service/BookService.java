package com.atguigu.amqp.service;

import com.atguigu.amqp.bean.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 *  @Author      :  AlanDing
 *  @Time        :  2019/11/18 下午11:41
 *  @File        :  BookService.java
 *  @Description :
 */

@Service
public class BookService {

    @RabbitListener(queues = "alanding.news")
    public void receive(Book book) {
        System.out.println("收到消息: " + book.getBookName() + " " + book.getAuthor());
    }

    @RabbitListener(queues = "alanding")
    public void receive02(Message message) {
        System.out.println(message.getBody());
        System.out.println(message.getMessageProperties());
    }
}
