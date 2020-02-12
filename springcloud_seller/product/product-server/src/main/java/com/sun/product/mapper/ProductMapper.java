package com.sun.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sun.product.entity.ProductInfo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zcm
 */
@Component
public interface ProductMapper extends BaseMapper<ProductInfo> {

}
