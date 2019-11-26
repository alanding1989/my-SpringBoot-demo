package com.atguigu.amqp;

import com.atguigu.amqp.bean.Book;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class Springboot02AmqpApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;

    @Test
    public void createExchange() {
        amqpAdmin.declareExchange(new DirectExchange("amqpAdmin.exchange"));
        System.out.println("创建完成");
    }

    @Test
    public void createQueue() {
        amqpAdmin.declareQueue(new Queue("amqpAdmin.queue", true));
        System.out.println("创建完成");
    }

    @Test
    public void binding() {
        Binding binding = new Binding("amqpAdmin.queue", DestinationType.QUEUE,
                                      "amqpAdmin.exchange", "amqpAdmin.queue", null);
        amqpAdmin.declareBinding(binding);
    }

    @Test
    public void deleteExchange() {
        amqpAdmin.deleteExchange("amqpAdmin.exchange");
        amqpAdmin.deleteQueue("amqpAdmin.queue");
        System.out.println("删除成功");
    }


    /**
     * 1、单播
     */
    @Test
    public void send() {
        // Message 需要自己构造一个，定义消息体内容和消息头
        // rabbitTemplate.send(exchenge, routeKey, message);

        // object默认当成消息体，只需要传入要发送的对象，　自动序列化发送给rabbitmq
        // rabbitTemplate.convertAndSend(exchange, routeKey, object);

        Map<String, Object> map = new HashMap<>();
        map.put("msg", "这是第一个消息");
        map.put("data", Arrays.asList("helloworld", 123, true));
        // 对象被默认序列化以后发送出去
        rabbitTemplate.convertAndSend("exchange.direct", "alanding.news", new Book("西游记", "吴承恩"));
    }

    // 接收数据，如何将数据自动转为JSON发送出去
    @Test
    public void receive() {
        Object o = rabbitTemplate.receiveAndConvert("alanding.news");
        System.out.println(o != null ? o.getClass() : null);
        System.out.println(o);
    }

    /**
     * 广播
     */
    @Test
    public void broadcastMsg() {
        // rabbitTemplate.convertAndSend("exchange.fanout", "", new Book("三国演义", "罗贯中"));
        rabbitTemplate.convertAndSend("exchange.fanout", "",
                                      new Book("红楼梦", "曹雪芹"));
    }
}
