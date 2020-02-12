package com.sun.order.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * 订单商品详情表
 * @author zcm
 */
@Data
public class OrderDetail {

    @TableField(value = "detail_id")
    private String id;
    private String orderId;
    private String productId;
    private String productPrice;
    private  Integer productQuantity;
    private String productIcon;
}
