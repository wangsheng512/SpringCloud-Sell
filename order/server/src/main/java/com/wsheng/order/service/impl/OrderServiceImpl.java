package com.wsheng.order.service.impl;

import com.wsheng.order.dataobject.OrderDetail;
import com.wsheng.order.dataobject.OrderMaster;
import com.wsheng.order.dto.OrderDTO;
import com.wsheng.order.enums.OrderStatusEnum;
import com.wsheng.order.enums.PayStatusEnum;
import com.wsheng.order.enums.ResultEnum;
import com.wsheng.order.exception.OrderException;
import com.wsheng.order.repository.OrderDetailRepository;
import com.wsheng.order.repository.OrderMasterRepository;
import com.wsheng.order.service.OrderService;
import com.wsheng.order.utils.KeyUtil;
import com.wsheng.product.client.ProductClient;
import com.wsheng.product.common.DecreaseStockInput;
import com.wsheng.product.common.ProductInfoOutput;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Auther: wsheng
 * @Date: 2018/10/19 17:15
 * @Description:
 */
@Service
public class OrderServiceImpl implements OrderService{


    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private ProductClient productClient;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniqueKey();
        //查询商品信息，调用商品服务
        List<String> list = orderDTO.getOrderDetailList().stream()
                .map(OrderDetail::getProductId)
                .collect(Collectors.toList());
        List<ProductInfoOutput> productInfoList = productClient.listForOrder(list);
        //计算总价
        BigDecimal orderAmout = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()){
            for (ProductInfoOutput productInfo: productInfoList){
                if (productInfo.getProductId().equals(orderDetail.getProductId())){
                    //单价*数量
                    orderAmout = productInfo.getProductPrice()
                            .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                            .add(orderAmout);
                    BeanUtils.copyProperties(productInfo,orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.genUniqueKey());
                    //订单详情入库
                    orderDetailRepository.save(orderDetail);
                }
            }
        }
        //扣库存
        List<DecreaseStockInput> decreaseStockInputList  = orderDTO.getOrderDetailList().stream()
                .map(e ->new DecreaseStockInput(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());
        productClient.decreaseStock(decreaseStockInputList);
        //订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmout);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(String orderId) {
        //1、先查询订单
        Optional<OrderMaster> orderMasterOptional =  orderMasterRepository.findById(orderId);
        if (!orderMasterOptional.isPresent()){
            throw new OrderException(ResultEnum.ORDER_NOT_EXITS);
        }
        //2、判断订单是否可以修改
        OrderMaster orderMaster = orderMasterOptional.get();
        if (orderMaster.getOrderStatus() != OrderStatusEnum.NEW.getCode()){
            throw  new OrderException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //3、修改状态(状态为完结)
        orderMaster.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        orderMasterRepository.save(orderMaster);
        //4、构造返回的orderdto,先查询订单详情
        List<OrderDetail> orderDetailList = orderDetailRepository.findOrderDetailByOrderId(orderId);
        if(CollectionUtils.isEmpty(orderDetailList)){
            throw new OrderException(ResultEnum.ORDER_DETAIL_NOT_EXITS);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        List<String> st = new ArrayList<>();
        st.add("ss");
        return orderDTO;
    }
}
