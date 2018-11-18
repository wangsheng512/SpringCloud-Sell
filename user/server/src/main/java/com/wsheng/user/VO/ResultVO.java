package com.wsheng.user.VO;

import lombok.Data;

/**
 * @Auther: wsheng
 * @Date: 2018/10/18 17:11
 * @Description:
 */
@Data
public class ResultVO<T> {

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 具体内容
     */
    private T data;
}