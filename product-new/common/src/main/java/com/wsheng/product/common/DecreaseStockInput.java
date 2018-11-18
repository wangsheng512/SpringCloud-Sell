package com.wsheng.product.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: wsheng
 * @Date: 2018/10/23 23:52
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DecreaseStockInput {

    private String productId;

    private Integer productQuantity;

}