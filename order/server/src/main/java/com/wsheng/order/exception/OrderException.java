package com.wsheng.order.exception;

import com.wsheng.order.enums.ResultEnum;

/**
 * @Auther: wsheng
 * @Date: 2018/10/22 16:20
 * @Description:
 */
public class OrderException extends RuntimeException {

    private Integer code;

    public OrderException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public OrderException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}