package com.sun.product.controller;

import com.sun.common.ProductInfoOutput;
import com.sun.product.VO.ProductInfoVO;
import com.sun.product.VO.ProductVO;
import com.sun.product.VO.ResultVO;
import com.sun.product.entity.ProductCategory;
import com.sun.product.entity.ProductInfo;
import com.sun.product.service.CategoryService;
import com.sun.product.service.ProductService;
import com.sun.product.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zcm
 */
@RestController
@RequestMapping("/product")
public class ProductController {


    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    /**
     * 查询上架产品
     * @return
     */
    @GetMapping("/list")
    public ResultVO<Object> list(){
        List<ProductInfo> productInfoList = productService.findUpAll();
        List<Integer> typeList = productInfoList.stream().map(ProductInfo::getCategoryType).collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findCategoryTypeIn(typeList);

        List<ProductVO> productVOList = new ArrayList<>();

        for (ProductCategory productCategory : productCategoryList){
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList){
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVoList(productInfoVOList);
            productVOList.add(productVO);
        }
        return ResultVOUtil.success(productVOList);
    }

    /**
     * 根据商品ids，查找商品信息
     * 跟订单服务调用
     * @param productIdList
     * @return
     */
    @PostMapping("/listForOrder")
    public List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList){
//        try {
//            Thread.sleep(2000);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        return productService.findList(productIdList);
    }
}
