package com.sun.order.service.impl;

import com.sun.common.ProductInfoOutput;
import com.sun.order.client.ProductClient;
import com.sun.order.dto.OrderDTO;
import com.sun.order.entity.OrderDetail;
import com.sun.order.entity.OrderMaster;
import com.sun.order.mapper.OrderDetailMapper;
import com.sun.order.mapper.OrderMasterMapper;
import com.sun.order.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zcm
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDetailMapper orderDetailMapper;

    @Autowired
    OrderMasterMapper orderMasterMapper;

    @Autowired
    ProductClient productClient;


    @Override
    public OrderDTO create(OrderDTO orderDTO) {

        //TODO 查询商品信息(调用商品服务)
        List<String> productList = orderDTO.getOrderDetailList().stream().map(OrderDetail::getProductId).collect(Collectors.toList());
        List<ProductInfoOutput> productInfoOutputList = productClient.listForOrder(productList);
        System.out.println(productInfoOutputList);
        //TODO 计算总价

        //TODO 扣库存

        //订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setId(orderMaster.getId());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMasterMapper.insert(orderMaster);
        return orderDTO;
    }

    @Override
    public OrderDTO finish(String orderId) {
        return null;
    }
}
