package com.sun.nine.lambda.cart;

import java.util.ArrayList;
import java.util.List;

public class CartService {


    private static List<Sku> cartSkuList = new ArrayList<Sku>(){
        {
            add(new Sku(1000,"无人机",4999.0,1,4999.0,SkuCategory.ELECTRONICS));
        }
    };

    public static List<Sku> getCartSkuList(){
        return cartSkuList;
    }

    /**
     * 返回购物车中电子产品的信息
     *  Version 1.0.0
     * @param cartSkuList
     * @return
     */
    public static List<Sku> filterElectronicsSkus(List<Sku> cartSkuList){
        List<Sku> result = new ArrayList<Sku>();
        for (Sku sku : cartSkuList) {
            if (SkuCategory.ELECTRONICS.equals(sku.getSkuCategory())){
                result.add(sku);
            }
        }
        return result;
    }

    /**
     * 返回购物车产品的信息
     *  Version 2.0.0
     * @param cartSkuList
     * @return
     */
    public static List<Sku> filterCategorySkus(List<Sku> cartSkuList,SkuCategory category){
        List<Sku> result = new ArrayList<Sku>();
        for (Sku sku : cartSkuList) {
            if (category.equals(sku.getSkuCategory())){
                result.add(sku);
            }
        }
        return result;
    }

    /**
     * 返回购物车产品的信息
     *  Version 3.0.0
     * @param cartSkuList
     * @return
     */
    public static List<Sku> filterCategorySkus(List<Sku> cartSkuList,SkuCategory category,Double totalPrice,boolean categroyOrPorice){
        List<Sku> result = new ArrayList<Sku>();
        for (Sku sku : cartSkuList) {
            if (
                    (categroyOrPorice && category.equals(sku.getSkuCategory()))
                    || (!categroyOrPorice && totalPrice < sku.getTotalPrice())
            ){
                result.add(sku);
            }
        }
        return result;
    }

    /**
     * 返回购物车产品的信息
     *  Version 4.0.0
     * @param cartSkuList
     * @return
     */
    public static List<Sku> filterCategorySkus(List<Sku> cartSkuList,CartPredict cartPredict){
        List<Sku> result = new ArrayList<Sku>();
        for (Sku sku : cartSkuList) {
            if (cartPredict.test(sku)){
                result.add(sku);
            }
        }
        return result;
    }
}
