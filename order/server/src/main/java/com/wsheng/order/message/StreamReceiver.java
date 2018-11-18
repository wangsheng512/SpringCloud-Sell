package com.wsheng.order.message;

import com.wsheng.order.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

/**
 * @Auther: wsheng
 * @Date: 2018/11/1 14:43
 * @Description:
 */
@Component
@EnableBinding(StreamClient.class)
@Slf4j
public class StreamReceiver {

    @StreamListener(StreamClient.INPUT)
    public void proces(OrderDTO message){
        log.info("StreamReceiver:{}",message);
    }
}
