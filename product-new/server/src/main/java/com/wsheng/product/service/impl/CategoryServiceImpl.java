package com.wsheng.product.service.impl;

import com.wsheng.product.dataobject.ProductCategory;
import com.wsheng.product.repository.ProductCategoryRepository;
import com.wsheng.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: wsheng
 * @Date: 2018/10/18 14:52
 * @Description:
 */
@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer>
                                                                  categoryTypeList) {
        return productCategoryRepository.findByCategoryTypeIn(categoryTypeList);
    }
}
