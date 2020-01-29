package com.sun.nine.lambda.cart;

public class ElectronicsPredict implements  CartPredict{
    public boolean test(Sku sku) {
        return sku.getSkuCategory().equals(SkuCategory.ELECTRONICS);
    }
}
