package com.wsheng.order.repository;

import com.wsheng.order.dataobject.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: wsheng
 * @Date: 2018/10/19 16:36
 * @Description:
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {
}

