package com.wsheng.user.enums;

import lombok.Getter;

/**
 * @Auther: wsheng
 * @Date: 2018/11/6 22:41
 * @Description:
 */
@Getter
public enum RoleEnum {

    BUYER(1,"买家"),

    SELLER(2, "卖家"),
    ;

    private Integer code;

    private String message;

    RoleEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
