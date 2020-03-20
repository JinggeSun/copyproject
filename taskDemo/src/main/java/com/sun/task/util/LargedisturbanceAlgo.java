package com.sun.task.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 远程测试大扰动算法
 *
 * @author WY-SUNJG
 *
 */
public class LargedisturbanceAlgo {

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
     * 计算转速差
     * 使用频差值计算转速差
     * @param pczValue 频差值
     * @return 保留3位小数点
     */
    public static Double calSpeedDif(Double pczValue) {
        Double speedDiffValue = pczValue*60;
        Double zsc = pointRound(speedDiffValue, 3);// 转速值
        return zsc;
    }

    /**
     * 计算调频理论补偿值
     * 1，如果频差值的绝对值小于0.033,则是0
     * 2，使用公式计算理论补偿值
     * 2019-01-06 新增趋势计算
     * 3.下降趋势，正常计算。上升趋势，取相反数
     * @param freDiffValue 频差值
     * @param speedDifValue 转速差
     * @param reatedPower 额定功率
     * @param trend 趋势 -1下降，1上升
     * @return 保留3位小数点
     */
    public static Double calFreCompen(Double freDiffValue,Double speedDifValue,int reatedPower,int trend){
        Double freCompen = 0D;
        double pczAbs = Math.abs(freDiffValue);
        if (pczAbs < 0.033){
            freCompen = 0D;
        }else{
            //绝对值
            double speedDifValueAbs = Math.abs(speedDifValue);
            //公式
            double freCompenValue = ((speedDifValueAbs-2)/3000*reatedPower)/0.05;
            freCompen = pointRound(freCompenValue,3);
        }

        if (trend > 0){
            //上升趋势，取相反数
            freCompen = freCompen * -1;
        }

        return freCompen;
    }


    /**
     * 判断远程测试是否发生扰动。 如果信号值为1，并且维持30s~300s，则认为是扰动。 将扰动的开始时间和结束时间放入map中返回。
     * 如果最后信号值为1，说明没有结束，返回值中，只有开始时间，没有结束时间。
     *
     * @param valueList
     * @param key
     *            map的key
     * @param val
     *            map的value信号值
     * @param lastTime
     *            上次的开始时间，上次结束为null。
     * @return list中的map：start 开始时间，end结束时间
     */
    public static List<Map<String, Object>> judgeLargedisturbance(
            List<Map<String, Object>> valueList, String key, String val,
            Date lastTime,String stKey,String enKey) {

        List<Map<String, Object>> mapList = new ArrayList<>();

        int outLimit = 0;// 是否超限
        Date beginTime = null;// 开始时间

        if (lastTime != null) {
            outLimit = 1;
            beginTime = lastTime;
        }

        if (valueList == null || valueList.size() == 0) {
            return mapList;
        }

        for (Map<String, Object> map : valueList) {

            Date date = (Date) map.get(key);
            Double value = Double.valueOf(map.get(val).toString());

            if (value == 1) {
                if (outLimit == 0) {
                    // 第一次超限，记录开始时间
                    beginTime = date;
                    outLimit = 1;
                }
            } else {
                if (outLimit == 1) {
                    // 之前有过一次超限，记录下来
                    // 1. 计算超限时长，如果在30s~300s以内才算正常数据
                    // 2. 正常数据记录下来
                    Long diff = (date.getTime() - 1000 - beginTime.getTime())/1000;
                    if (diff >= 30 && diff <= 300) {
                        // 正常数据
                        Map<String, Object> ma = new HashMap<String, Object>();
                        ma.put(stKey, beginTime);// 开始时间
                        ma.put(enKey, date);// 结束时间
                        mapList.add(ma);
                    }
                    outLimit = 0;
                }
            }
        }
        // 当最后一个时间也超限时，此时，只有开始时间，没有结束时间
        if (outLimit == 1) {

            /**
             * 2019-12-27
             * 最后一个超限
             */
            //最后一个值的时间.如果跳变的第一个时间与最后的时间超过300秒，则无效扰动，不需要记录
            Date date = (Date) valueList.get(valueList.size()-1).get(key);
            Long diff = (date.getTime() - 1000 - beginTime.getTime())/1000;
            if (diff < 300) {
                Map<String, Object> ma = new HashMap<String, Object>();
                ma.put(stKey, beginTime);// 开始时间
                ma.put(enKey, null);// 结束时间
                mapList.add(ma);
            }
        }

        return mapList;
    }

    /**
     * 计算遥调开始时间 在数据中，找到低于范围的第一个数据。作为第1秒。返回第0秒的时间 查找曲线，计算是上升趋势还是下降趋势 上升1，下降-1
     * @param valueList
     * @param key
     * @param val
     * @param limit
     * @param ytRangeMin
     * @param ytRangeMax
     * @param ytNormalValue
     * @param timeKey
     * @param trendKey
     * @param beatFlag 是否跳转作为开始时间 1跳转
     * @return
     */
    public static Map<String, Object> calYtStartTime(
            List<Map<String, Object>> valueList, String key, String val,
            Double limit, Double ytRangeMin, Double ytRangeMax,
            Double ytNormalValue,String timeKey,String trendKey,int beatFlag) {

        Map<String, Object> resMap = new HashMap<>();

        if (valueList.size() == 0){
            return resMap;
        }

        for (Map<String, Object> map : valueList) {

            Date date = (Date) map.get(key);
            Double value = Double.valueOf(map.get(val).toString());

            // 第一步，判断值的范围，如果不在范围内，则不进行计算
            // 因为数据子站传递过来的，连续的，如果第一条不在范围内，剩下的数据也不在范围内
            if (ytRangeMin > value || value > ytRangeMax) {
                break;
            }

            // 第二步，判断起始位置
            if (value <= (ytNormalValue - limit)
                    || value >= (ytNormalValue + limit)) {

                // 当前值是遥调开始第1秒,第0秒获取前一秒
                resMap.put(timeKey, new Date(date.getTime() - 1000));
                // 下降
                if (value <= (ytNormalValue - limit)) {
                    resMap.put(trendKey, -1);
                }
                // 上升
                if (value >= (ytNormalValue + limit)) {
                    resMap.put(trendKey, 1);
                }
                return resMap;
            }
        }

        //跳转14s作为开始时间
        if (beatFlag == 1){
            if (resMap == null || resMap.size() == 0){
                Date st = (Date) valueList.get(0).get(key);
                resMap.put(timeKey,new Date(st.getTime() + 13 * 1000));
                resMap.put(trendKey, 0);
            }
        }
        return resMap;
    }

    /**
     * 单阀或顺序阀判断（按遥调值跳变时刻计算） 阀门开度是否一致 判断数组里的数值是否一致 一致 返回 0 不一致 返回 1
     *
     * @return
     */
    public static int calValveOpening(Double[] valueArr) {

        if (valueArr.length == 0){
            return 0;
        }

        Arrays.sort(valueArr);

        if (valueArr[0] == valueArr[valueArr.length - 1]) {
            return 0;
        }
        return 1;
    }

    /**
     * 机组运行方式判断 判断失败返回5
     *
     * @param glzkValue
     *            锅炉主控自动状态
     * @param qjzkValue
     *            汽机主控自动状态
     * @return
     */
    public static int unitOperationMode(Double glzkValue, Double qjzkValue) {

        int res = 5;

        if (glzkValue == 1 && qjzkValue == 1) {
            res = 1;
        } else if (glzkValue == 1 && qjzkValue == 0) {
            res = 2;
        } else if (glzkValue == 0 && qjzkValue == 1) {
            res = 3;
        } else if (glzkValue == 0 && qjzkValue == 0) {
            res = 4;
        }
        return res;
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

    /**
     * 计算不等率
     * @param speedDif
     * @param powerMax
     * @param powerMin
     * @param testBeforePower
     * @param ratedPower
     * @param trend
     * @param factorKey 不等率合格与否key
     * @param factorValueKey 不等率key
     * @return
     */
    public static Map<String,Object> calDiversityFactory(Double speedDif,Double powerMax,Double powerMin,Double testBeforePower,int ratedPower,Integer trend,String factorKey,String factorValueKey){

        Double value = 0.0;
        double n0 = 3000;

        //1. 转速差 绝对值
        Double speedDifAbs = Math.abs(speedDif);

        //新增计算前提，转速不等率：遥调值增加时，取极大值，遥调值减少时，取极小值
        Double powerDifAbs = 0D;
        if (trend == 1){
            //上升趋势，使用极小值
            powerDifAbs = Math.abs(powerMin-testBeforePower);
        }else{
            //下降趋势，使用极大值
            powerDifAbs = Math.abs(powerMax-testBeforePower);
        }

        //公式 value = ((speed_Dif_abs-2)/n0)/power_dif_abs*volume;

        if(powerDifAbs==0.0 || n0==0.0 || ratedPower==0.0){
            value = 0.0;
        }else{
            value = ((speedDifAbs-2)/n0)/powerDifAbs*ratedPower*100;
        }
        value =  pointRound(value, 3);

        Map<String,Object> resMap = new HashMap<>();
        resMap.put(factorValueKey,value);
        resMap.put(factorKey, value<(0.05*100)?0:1);//不等率

        return resMap;

    }

    /**
     * 判断数据质量：如果2个原始值之间的时间差超过2秒及其以上
     * @param poHisArr
     * @param timeKey
     * @return -1 不合格，1合格
     */
    public static int judgeQuality(List<Map<String, Object>> poHisArr,String timeKey) {

        int flag = 1;
        Date indexD = null;
        for (Map<String, Object> map : poHisArr){
            Date date = (Date) map.get(timeKey);
            if (indexD == null){
                indexD = date;
            }else{
                if ((date.getTime() - indexD.getTime())>=1000*2){
                    flag = -1;
                    break;
                }else{
                    indexD = date;
                }
            }
        }

        return flag;
    }

}
