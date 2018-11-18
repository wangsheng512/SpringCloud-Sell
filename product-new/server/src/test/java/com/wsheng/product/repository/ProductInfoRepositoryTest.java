package com.wsheng.product.repository;

import com.wsheng.product.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;



@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {


    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    public void findByProductStatus() throws Exception {
        List<ProductInfo> list = productInfoRepository.findByProductStatus(0);
        Assert.assertTrue(list.size()>0);
    }

    @Test
    public void findByProductIdIn() throws Exception {

        List<ProductInfo> list =
                productInfoRepository.findByProductIdIn(Arrays.asList("123456","123666"));
        Assert.assertTrue(list.size()>0);
    }

}