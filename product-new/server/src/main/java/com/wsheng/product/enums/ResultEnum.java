package com.wsheng.product.enums;

import lombok.Getter;

/**
 * @Auther: wsheng
 * @Date: 2018/10/18 11:27
 * @Description:
 */
@Getter
public enum ResultEnum {

    PRODUCT_NOT_EXIST(1, "商品不存在"),
    PRODUCT_STOCK_ERROR(2, "库存有误"),
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}