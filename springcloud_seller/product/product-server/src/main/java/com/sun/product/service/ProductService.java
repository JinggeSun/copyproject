package com.sun.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sun.common.ProductInfoOutput;
import com.sun.product.entity.ProductInfo;

import java.util.List;

/**
 * @author zcm
 */
public interface ProductService extends IService<ProductInfo> {

    /**
     * 查询所有的在架商品
     * @return
     */
    List<ProductInfo> findUpAll();

    /**
     * 根据商品id列表查找商品
     * @param productIdList
     * @return
     */
    List<ProductInfoOutput> findList(List<String> productIdList);
}
