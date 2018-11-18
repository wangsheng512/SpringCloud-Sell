package com.wsheng.user.enums;

import lombok.Getter;

/**
 * @Auther: wsheng
 * @Date: 2018/10/19 16:38
 * @Description:
 */
@Getter
public enum ResultEnum {
    LOGIN_FAIL(1, "登录失败"),

   ROLE_ERROR(2,"角色权限有误"),
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}