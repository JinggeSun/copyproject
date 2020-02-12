package com.sun.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.product.entity.ProductCategory;
import com.sun.product.mapper.CategoryMapper;
import com.sun.product.mapper.ProductMapper;
import com.sun.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zcm
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, ProductCategory> implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<ProductCategory> findCategoryTypeIn(List<Integer> categoryTypeList) {
        QueryWrapper<ProductCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("category_type",categoryTypeList);
        return categoryMapper.selectList(queryWrapper);
    }
}
