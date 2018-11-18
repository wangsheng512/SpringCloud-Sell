package com.wsheng.product.exception;

import com.wsheng.product.enums.ResultEnum;

/**
 * @Auther: wsheng
 * @Date: 2018/10/23 16:18
 * @Description:
 */
public class ProductException extends RuntimeException {

    private Integer code;

    public ProductException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public ProductException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
