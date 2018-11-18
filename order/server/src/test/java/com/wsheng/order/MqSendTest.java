package com.wsheng.order;

import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Auther: wsheng
 * @Date: 2018/10/31 21:02
 * @Description:
 */
@Component
public class MqSendTest extends OrderApplicationTests{


    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void send(){
        amqpTemplate.convertAndSend("myQueue","now"+ new Date());
    }

    @Test
    public void sendOrder(){
        amqpTemplate.convertAndSend("myOrder", "computer","now"+ new Date());
    }
}
