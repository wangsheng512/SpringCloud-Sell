package com.wsheng.order.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 接收mq消息
 * @Auther: wsheng
 * @Date: 2018/10/31 20:34
 * @Description:
 */
@Slf4j
@Component
public class MqReceiver {


    //1、@RabbitListener(queues = "myQueue")
    //2、自动创建队列@RabbitListener(queuesToDeclare = @Queue("myQueue"))
    //3、自动创建，Exchange和Queue绑定
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("myQueue"),
            exchange = @Exchange("myExchange")
    ))
    public void process(String message){
        log.info("MyReceiver:{}",message);
    }

    /**
     * 电脑服务
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myOrder"),
            key = "computer",
            value = @Queue("computerOrder")
    ))
    public void processComputer(String message){
        log.info("computer MyReceiver:{}",message);
    }

    /**
     * 水果服务
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myOrder"),
            key = "fruit",
            value = @Queue("fruitOrder")
    ))
    public void processFruit(String message){
        log.info("fruit MyReceiver:{}",message);
    }

}
