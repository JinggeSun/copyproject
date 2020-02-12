package com.sun.order.dto;

import com.sun.order.entity.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author zcm
 */
@Data
public class OrderDTO {

    private String id;

    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private String buyerOpenid;
    private BigDecimal orderAmount;
    private Integer orderStatus;
    private Integer payStatus;

    private List<OrderDetail> orderDetailList;

}
