package com.sun.order.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.beans.BeanInfo;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单表
 * @author zcm
 */
@Data
public class OrderMaster {

    @TableField(value = "order_id")
    private String id;

    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private String buyerOpenid;
    private BigDecimal orderAmount;
    private Integer orderStatus;
    private Integer payStatus;
    private Date createTime;
    private Date updateTime;
}
