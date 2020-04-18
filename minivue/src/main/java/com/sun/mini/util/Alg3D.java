package com.sun.mini.util;

import java.util.*;

/**
 * @author zcm
 */
public class Alg3D {

    static int[] zkOrder = {50,60,70,80,90,100};


    /**
     * Double
     * @param originData [主控指令，有功功率，主汽压力，时间戳，主键]
     * @param ratedPower 额定规律
     * @param powerMin 有功功率最小值
     * @param preMax 主汽压力最大值
     * @param preMin 主汽压力最小值
     */
    public static void handlerArea(List<Double[]> originData,Integer ratedPower,Integer powerMin,Double preMax,Double preMin){

        List<Integer> powerAreaList = powerArea(ratedPower,powerMin);

        List<Integer> preAreaList = preArea(preMax,preMin);

        Map<String,List<Double[]>> resultMap = new HashMap<>();

        for (int i=0;i<powerAreaList.size()-1;i++){

            int yMin = powerAreaList.get(i);
            int yMax = powerAreaList.get(i+1);

            for (int j=0;j<zkOrder.length-1;j++){

                int xMin = zkOrder[j];
                int xMax = zkOrder[j+1];

                for (int m=0;m<preAreaList.size()-1;m++){

                    int zMin = preAreaList.get(m);
                    int zMax = preAreaList.get(m+1);

                    for (Double[] origin : originData){
                        Double valueX = origin[0];
                        Double valueY = origin[1];
                        Double valueZ = origin[2];

                        if (valueX<xMax && valueX>= xMin && valueY<yMax && valueY>= yMin && valueZ < zMax && valueZ >= zMin){
                            String key = xMin+","+xMax+","+yMin+","+yMax +"," + zMin + "," + zMax;

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
        }

        System.err.println(resultMap);

        //[有功功率，主控指令，主汽压力，时间戳，主键，类型]

        List<Double[]> resList = new ArrayList<>();

        //处理resultMap
        // x,y,大小，月份，原始数据，主键，标志
        //[有功功率，主控指令，"" , 主汽压力，时间戳，主键，类型]
        double tag = 0d;

        for (String key :resultMap.keySet()){

            List<Double[]> doubles = resultMap.get(key);
            //计算拟合值
            Double allPowerVal = 0D;
            Double allOrderVal = 0D;
            Double allPreVal = 0D;

            for (Double[] d : doubles){
                //1。 填充原始数据
                Double[] resArr = new Double[8];
                /*
                 * x轴
                 */
                resArr[0] = d[1];
                /*
                 * y轴
                 */
                resArr[1] = d[0];
                /*
                 *
                 */
                resArr[2] = 0D;
                /*
                 * z轴
                 */
                resArr[3] = d[2];
                /*
                 * 时间戳
                 */
                resArr[4] = d[3];
                /*
                 * 1原始数据
                 */
                resArr[5] = 1D;
                /*
                 * 主键
                 */
                resArr[6] = d[4];
                /*
                 * 组
                 */
                resArr[7] = tag;

                resList.add(resArr);

                allOrderVal += resArr[0];
                allPowerVal += resArr[1];
                allPreVal += resArr[3];
            }

            //计算拟合值
            if (allOrderVal != 0 && allPowerVal != 0 && allPreVal != 0){


                Double[] resArr = new Double[8];
                /*
                 * x轴
                 */
                resArr[0] = allOrderVal*1.0/doubles.size();
                /*
                 * y轴
                 */
                resArr[1] = allPowerVal*1.0/doubles.size();
                /*
                 *
                 */
                resArr[2] = 0D;
                /*
                 * z轴
                 */
                resArr[3] = allPreVal*1.0/doubles.size();
                /*
                 * 时间戳
                 */
                resArr[4] = 0D;
                /*
                 * 2拟合数据
                 */
                resArr[5] = 1D;
                /*
                 * 主键
                 */
                resArr[6] = 0d;
                /*
                 * 组
                 */
                resArr[7] = tag;

                resList.add(resArr);

            }

            tag += 1;
        }

        System.out.println(resList);
    }

    /**
     *
     * @param preMax
     * @param preMin
     * @return
     */
    private static List<Integer> preArea(Double preMax, Double preMin) {
        //将preMax进行整数化处理
        List<Integer> data = new ArrayList<>();
        Integer value = preMax.intValue();
        while (value >= preMin){
            data.add(value);
            value -= 10;
        }
        data.add(value);
        data.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        return data;
    }

    /**
     *
     * @param ratedPower
     * @param powerMin
     * @return
     */
    public static  List<Integer> powerArea (Integer ratedPower,Integer powerMin){

        List<Integer> data = new ArrayList<>();
        while (ratedPower >= powerMin){
            data.add(ratedPower);
            ratedPower -= 10;
        }
        data.add(ratedPower);
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
        Double[] d1 = {95d,325d,20d,11D,100d};
        Double[] d2 = {75d,315d,20d,11D,100d};
        Double[] d3 = {65d,285d,30d,11D,100d};

        List<Double[]> doubles = new ArrayList<>();
        doubles.add(d1);
        doubles.add(d2);
        doubles.add(d3);
        handlerArea(doubles,330,280,30d,20d);
    }

}
