package com.wsheng.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: wsheng
 * @Date: 2018/10/23 16:14
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {

    /**
     * 商品ID
     */
    private String productId;

    /**
     * 商品数量
     */
    private Integer productQuantity;
}
