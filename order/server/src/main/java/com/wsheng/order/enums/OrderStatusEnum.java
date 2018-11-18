package com.wsheng.order.enums;

import lombok.Getter;

/**
 * @Auther: wsheng
 * @Date: 2018/10/19 16:38
 * @Description:
 */
@Getter
public enum OrderStatusEnum {
    NEW(0, "新订单"),
    FINISHED(1, "完结"),
    CANCEL(2, "取消"),
    ;
    private Integer code;

    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}