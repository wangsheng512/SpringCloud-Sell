package com.wsheng.product.controller;

import com.wsheng.product.VO.ProductInfoVO;
import com.wsheng.product.VO.ResultVO;
import com.wsheng.product.common.DecreaseStockInput;
import com.wsheng.product.common.ProductInfoOutput;
import com.wsheng.product.service.ProductService;
import com.wsheng.product.dto.CartDTO;
import com.wsheng.product.VO.ProductVO;
import com.wsheng.product.dataobject.ProductCategory;
import com.wsheng.product.dataobject.ProductInfo;
import com.wsheng.product.service.CategoryService;
import com.wsheng.product.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: wsheng
 * @Date: 2018/10/17 22:48
 * @Description:
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 1、查询所有在架的商品
     * 2、获取类目type列表
     * 3. 从数据库查询类目
     * 4. 构造数据
     * @return
     */
    @GetMapping("/list")
    public ResultVO<ProductVO> list(){

        //1、查询所有在架的商品
        List<ProductInfo> productInfoList = productService.findUpAll();


        //2、获取类目type列表
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());

        //3. 从数据库查询类目
        List<ProductCategory> categoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        //4. 构造数据
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory: categoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo: productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        return ResultVOUtil.success(productVOList);

    }

    /**
     * 获取商品列表，给订单服务用
     * @param productIdList
     * @return
     */
    @PostMapping("/listForOrder")
    public List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList){
        return productService.findList(productIdList);
    }

    /**
     * 扣库存
     * @param decreaseStockInputList
     */
    @PostMapping("/decreaseStock")
    public void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList){
        productService.decreaseStock(decreaseStockInputList);
    }
}
