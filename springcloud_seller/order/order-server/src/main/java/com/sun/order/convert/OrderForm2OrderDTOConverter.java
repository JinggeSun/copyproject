package com.sun.order.convert;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.order.dto.OrderDTO;
import com.sun.order.entity.OrderDetail;
import com.sun.order.enums.ResultEnum;
import com.sun.order.exception.OrderException;
import com.sun.order.form.OrderForm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zcm
 */
public class OrderForm2OrderDTOConverter {


    public static OrderDTO convert(OrderForm orderForm) {
        Gson gson = new Gson();

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();

        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),new TypeToken<List<OrderDetail>>(){}.getType());
        }catch (Exception e){
            throw new OrderException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
