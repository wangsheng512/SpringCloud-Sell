package com.wsheng.product.service;

import com.wsheng.product.dataobject.ProductCategory;

import java.util.List;

/**
 * @Auther: wsheng
 * @Date: 2018/10/18 11:48
 * @Description:
 */
public interface CategoryService {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
