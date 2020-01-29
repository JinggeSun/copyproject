package com.sun.nine.lambda.cart;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.List;

public class Test1Version {

    @Test
    public void filterElectronicsSkus(){
        List<Sku> skuList = CartService.getCartSkuList();

        List<Sku> result = CartService.filterElectronicsSkus(skuList);

        System.out.println(JSON.toJSONString(result,true));

    }

    @Test
    public void filterCategroySkus(){
        List<Sku> skuList = CartService.getCartSkuList();

        List<Sku> result = CartService.filterCategorySkus(skuList,SkuCategory.ELECTRONICS);

        System.out.println(JSON.toJSONString(result,true));

    }

    @Test
    public void filterSkus(){
        List<Sku> skuList = CartService.getCartSkuList();

        CartPredict cartPredict = new ElectronicsPredict();

        List<Sku> result = CartService.filterCategorySkus(skuList,cartPredict);

        System.out.println(JSON.toJSONString(result,true));

    }

    @Test
    public void filterSkusC(){
        List<Sku> skuList = CartService.getCartSkuList();

        List<Sku> result = CartService.filterCategorySkus(skuList,(s)-> s.getTotalPrice()>1000);

        System.out.println(JSON.toJSONString(result,true));

    }

}
