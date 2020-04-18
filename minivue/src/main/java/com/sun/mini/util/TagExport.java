package com.sun.mini.util;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author zcm
 */
public class TagExport {

    public static void main(String[] args) {

        //1。 获取遥调值/反馈值
        //2。 计算频差

    }

    /**
     * 折算频差
     * @param diffValue,有序
     * @return
     */
    List<Double> conversionDiffValue(List<Double> diffValue){
        return null;
    }





    /**
     * 根据频率换算公式，转换为1hz
     * @param hz
     * @return
     */
    private static Double getCoefficient(Double hz) {
        return (0.1-0.033)/(hz-0.033);
    }

    /**
     *  这算值公式
     *  折算值=0s值+(当前值-0s值)*(0.1-0.033)/(当前秒的折算前频差-0.033)
     * @param zeroValue
     * @param currentValue
     * @param currentDiffValue
     */
    public static Double convertFormula(Double zeroValue,Double currentValue,Double currentDiffValue){
        return zeroValue + (currentValue - zeroValue) * (0.1-0.033) / (currentDiffValue - 0.033);
    }

    /**
     * 计算频差值 1，使用公式转换 频率 2，减去50 频差 3，保留3位小数点
     *
     * @param ytValue
     *            遥调值/反馈值
     * @return 保留3位小数点
     */
    public static Double calFreDiff(Double ytValue) {
        Double rate = 0.4 * (ytValue - 9800) / 400 + 49.8;
        Double freDiffValue = rate - 50;
        Double pcz = pointRound(freDiffValue, 3);// 频差值
        return pcz;
    }

    /**
     * 为了保证算法完成，单独一个方法计算小数点
     * 保留小数点位数。
     * @param v 值
     * @param scale 小数位数，不能小于0
     * @return
     */
    public static double pointRound(double v, int scale) {
        if (scale < 0){
            return v;
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

}
