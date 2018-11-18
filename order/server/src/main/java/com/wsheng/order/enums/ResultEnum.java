package com.wsheng.order.enums;

import lombok.Getter;

/**
 * @Auther: wsheng
 * @Date: 2018/10/19 16:38
 * @Description:
 */
@Getter
public enum ResultEnum {
    PARAM_ERROR(1, "参数错误"),
    CART_EMPTY(2, "购物车为空"),
    ORDER_NOT_EXITS(3,"订单不存在"),
    ORDER_STATUS_ERROR(4,"订单状态错误"),
    ORDER_DETAIL_NOT_EXITS(5,"订单详情不存在"),

    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}