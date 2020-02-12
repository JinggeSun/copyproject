package com.sun.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sun.product.entity.ProductCategory;
import com.sun.product.entity.ProductInfo;

import java.util.List;

/**
 * @author zcm
 */
public interface CategoryService extends IService<ProductCategory> {
    /**
     * 根据分类id列表，查找所有分类
     * @param categoryTypeList
     * @return
     */
    List<ProductCategory> findCategoryTypeIn(List<Integer> categoryTypeList);
}
