package com.sun.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.common.ProductInfoOutput;
import com.sun.product.entity.ProductInfo;
import com.sun.product.enums.ProductStatusEnum;
import com.sun.product.mapper.ProductMapper;
import com.sun.product.service.ProductService;
import com.sun.xml.internal.ws.addressing.ProblemAction;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zcm
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper,ProductInfo> implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<ProductInfo> findUpAll() {
        QueryWrapper<ProductInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_status", ProductStatusEnum.UP.getCode());
        return productMapper.selectList(queryWrapper);
    }


    @Override
    public List<ProductInfoOutput> findList(List<String> productIdList) {

        QueryWrapper<ProductInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("product_id",productIdList);
        List<ProductInfo> productInfoList = productMapper.selectList(queryWrapper);

        return  productInfoList.stream().map(e->{
            ProductInfoOutput productInfoOutput = new ProductInfoOutput();
            productInfoOutput.setProductId(e.getId());
            BeanUtils.copyProperties(e,productInfoOutput);
            return productInfoOutput;
        }).collect(Collectors.toList());
    };
}
