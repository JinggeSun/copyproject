package com.sun.mini.util;

import java.util.*;

/**
 * @author zcm
 */
public class Alg {

    static int[] zkOrder = {50,60,70,80,90,100};

    public static List<Integer> calZkOrder(Double maxOrder,Double minOrder){
        //将maxOrder改为被5整出
        Integer max = paddingFive(maxOrder);
        Integer min = minOrder.intValue();
        return powerArea(max,min,5);
    }

    /**
     * 处理数据，归位5
     * @param value
     * @return
     */
    public static Integer paddingFive(Double value){

        int resValue = 0;

        String valueStr = value.intValue() + "";
        int last = Integer.parseInt(valueStr.substring(valueStr.length()-1,valueStr.length()));

        if (last >= 5){
            resValue = value.intValue() + 10 - last;
        }else{
            resValue = value.intValue() + 5 -last;
        }

        return resValue;

    }


    /**
     * Double
     * @param originData [主控指令，有功功率，时间戳，主键]
     * @param ratedPower 额定规律
     * @param powerMin 有功功率最小值
     */
    public static void handlerArea(List<Double[]> originData,Integer ratedPower,Integer powerMin){

        List<Integer> powerAreaList = powerArea(ratedPower,powerMin,10);

        Map<String,List<Double[]>> resultMap = new HashMap<>();

        for (int i=0;i<powerAreaList.size()-1;i++){

            int yMin = powerAreaList.get(i);
            int yMax = powerAreaList.get(i+1);

            for (int j=0;j<zkOrder.length-1;j++){

                int xMin = zkOrder[j];
                int xMax = zkOrder[j+1];

                for (Double[] origin : originData){
                    Double valueX = origin[0];
                    Double valueY = origin[1];

                    System.out.println(valueX+ " " + valueY + " " + xMin + " " + xMax + " " + yMin +" " +yMax);

                    if (valueX<xMax && valueX>= xMin && valueY<yMax && valueY>= yMin){
                        String key = xMin+","+xMax+","+yMin+","+yMax;

                        if (resultMap.containsKey(key)){
                            List<Double[]> list = resultMap.get(key);
                            list.add(origin);
                            resultMap.put(key,list);
                        }else{
                            List<Double[]> list = new ArrayList<>();
                            list.add(origin);
                            resultMap.put(key,list);
                        }
                    }
                }
            }
        }

        System.err.println(resultMap);

        List<List<Double[]>> resList = new ArrayList<>();

        List<Double[]> originList = new ArrayList<>();
        List<Double[]> matchList = new ArrayList<>();

        //处理resultMap
        // x,y,大小，月份，原始数据，主键，标志
        //[89,330,27662440,'09','原始数据',11，1]]
        double tag = 0d;

        for (String key :resultMap.keySet()){

            List<Double[]> doubles = resultMap.get(key);
            //计算拟合值
            Double allPowerVal = 0D;
            Double allOrderVal = 0D;

            for (Double[] d : doubles){
                //1。 填充原始数据
                Double[] resArr = new Double[7];
                /*
                 * x轴
                 */
                resArr[0] = d[0];
                /*
                 * y轴
                 */
                resArr[1] = d[1];
                /*
                 * 散点大小
                 */
                resArr[2] = 27662440D;
                /*
                 * 时间戳
                 */
                resArr[3] = d[2];
                /*
                 * 1原始数据
                 */
                resArr[4] = 1D;
                /*
                 * 主键
                 */
                resArr[5] = d[3];
                /*
                 * 组
                 */
                resArr[6] = tag;

                originList.add(resArr);

                allOrderVal += resArr[0];
                allPowerVal += resArr[1];
            }

            //计算拟合值
            if (allOrderVal != 0 && allPowerVal != 0){
                Double[] resArr = new Double[7];
                /*
                 * x轴
                 */
                resArr[0] = allOrderVal*1.0/doubles.size();
                /*
                 * y轴
                 */
                resArr[1] = allPowerVal*1.0/doubles.size();
                /*
                 * 散点大小
                 */
                resArr[2] = 27662440D;
                /*
                 * 时间戳
                 */
                resArr[3] = 0D;
                /*
                 * 2拟合数据
                 */
                resArr[4] = 2D;
                /*
                 * 主键
                 */
                resArr[5] = 0D;
                /*
                 * 组
                 */
                resArr[6] = tag;

                matchList.add(resArr);

            }

            tag += 1;
        }
        resList.add(originList);
        resList.add(matchList);

        System.out.println(resList);
    }

    /**
     *
     * @param max
     * @param min
     * @return
     */
    public static  List<Integer> powerArea (Integer max,Integer min,Integer diff){

        List<Integer> data = new ArrayList<>();
        while (max >= min){
            data.add(max);
            max -= diff;
        }
        data.add(max);
        data.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        return data;
    }

    public static void main(String[] args) {
       // System.out.println(powerArea(330,288));
//        Double[] d1 = {95d,325d,11D,100d};
//        Double[] d2 = {75d,315d,11D,100d};
//        Double[] d3 = {65d,285d,11D,100d};
//
//        List<Double[]> doubles = new ArrayList<>();
//        doubles.add(d1);
//        doubles.add(d2);
//        doubles.add(d3);
//        handlerArea(doubles,330,280);

        System.out.println(calZkOrder(100.11,44.0));
    }

}
