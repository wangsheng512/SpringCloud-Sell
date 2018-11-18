package com.wsheng.order.VO;

import lombok.Data;

/**
 * @Auther: wsheng
 * @Date: 2018/10/22 16:19
 * @Description:
 */
@Data
public class ResultVO<T> {

    private Integer code;

    private String msg;

    private T data;
}
