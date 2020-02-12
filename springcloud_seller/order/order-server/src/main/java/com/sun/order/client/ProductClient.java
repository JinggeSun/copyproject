package com.sun.order.client;

import com.sun.common.ProductInfoOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author zcm
 */
@FeignClient(name="product-server")
public interface ProductClient {

    /**
     * 根据商品ids 获取商品信息
     * @param productIdList
     * @return
     */
    @PostMapping("/product/listForOrder")
    List<ProductInfoOutput> listForOrder(@RequestBody  List<String> productIdList);
}
