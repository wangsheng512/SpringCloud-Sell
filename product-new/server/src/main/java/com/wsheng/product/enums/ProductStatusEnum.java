package com.wsheng.product.enums;

import lombok.Getter;

/**
 * @Auther: wsheng
 * @Date: 2018/10/18 11:29
 * @Description:
 */
@Getter
public enum ProductStatusEnum {
    UP(0, "在架"),
    DOWN(1, "下架"),
    ;

    private Integer code;

    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}