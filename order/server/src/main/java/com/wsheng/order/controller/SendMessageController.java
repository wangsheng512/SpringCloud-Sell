package com.wsheng.order.controller;

import com.wsheng.order.dto.OrderDTO;
import com.wsheng.order.message.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Auther: wsheng
 * @Date: 2018/11/1 14:55
 * @Description:
 */
@RestController
public class SendMessageController {


    @Autowired
    private StreamClient streamClient;

    @GetMapping("/sendMessage")
    public void process(){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId("123412431515");
        orderDTO.setBuyerName("ws");
        streamClient.output().send(MessageBuilder.withPayload(orderDTO).build());
    }
}
