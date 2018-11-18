package com.wsheng.order.repository;

import com.wsheng.order.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Auther: wsheng
 * @Date: 2018/10/19 16:36
 * @Description:
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

    List<OrderDetail> findOrderDetailByOrderId(String orderId);
}

