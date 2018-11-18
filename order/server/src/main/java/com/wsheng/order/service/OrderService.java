package com.wsheng.order.service;

import com.wsheng.order.dto.OrderDTO;

/**
 * @Auther: wsheng
 * @Date: 2018/10/19 17:09
 * @Description:
 */
public interface OrderService {

    /**
     * 创建订单
     * @param orderDTO
     * @return
     */
    OrderDTO create(OrderDTO orderDTO);

    /**
     * 完结订单(只能卖家操作)
     * @param orderId
     * @return
     */
    OrderDTO finish(String orderId);
}
