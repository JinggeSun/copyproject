package com.sun.nine.lambda.cart;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sku {

    private Integer skuId;

    private String skuName;

    private Double skuPrice;

    private Integer totalNum;

    private Double totalPrice;

    private Enum skuCategory;
}
