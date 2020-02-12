package com.sun.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author zcm
 */
@Data
public class ProductCategory {

    @TableId(value="category_id")
    private Integer id;
    private String categoryName;
    private Integer categoryType;
    private Date createTime;
    private Date updateTime;
}
