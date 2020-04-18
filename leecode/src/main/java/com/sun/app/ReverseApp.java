package com.sun.app;

import java.util.concurrent.ExecutionException;

/**
 * 整数反转
 */
public class ReverseApp {

    public static int reverse(int x) {
        String key = "";
        String xStr = x+"";
        if (x<0){
            key = "-";
            xStr = xStr.substring(1);
        }

        StringBuilder rStr = new StringBuilder();
        rStr.append(key);
        String[] xArr = xStr.split("");

        for (int i=xArr.length-1;i>=0;i--){
            rStr.append(xArr[i]);
        }
        try {
            int value = Integer.parseInt(rStr.toString());
            return value;
        }catch (Exception e){
            return 0;
        }
    }

    public static void main(String[] args) {
        System.out.println(cal(1534236469));
    }

    static int cal(int x){

        long value = 0L;

        while (x != 0){

            int y = x % 10;
            x = x / 10;
            value = value * 10L + y;
        }

        try {
            return Integer.parseInt(value+"");
        }catch (Exception e){
            return 0;
        }
    }
}
