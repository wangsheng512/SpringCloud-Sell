package com.wsheng.product.service;

import com.wsheng.product.common.DecreaseStockInput;
import com.wsheng.product.common.ProductInfoOutput;
import com.wsheng.product.dto.CartDTO;
import com.wsheng.product.dataobject.ProductInfo;

import java.util.List;

/**
 * @Auther: wsheng
 * @Date: 2018/10/18 11:17
 * @Description:
 */
public interface ProductService {

    /**
     * 查询所有在架商品列表
     */
    List<ProductInfo> findUpAll();

    /**
     * 查询商品列表
     * @param productIdList
     * @return
     */
    List<ProductInfoOutput> findList(List<String> productIdList);

    /**
     * 扣库存
     * @param decreaseStockInputList
     */
    void decreaseStock(List<DecreaseStockInput> decreaseStockInputList);
}