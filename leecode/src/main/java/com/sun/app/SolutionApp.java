package com.sun.app;

/**
 * 两数只和
 */
public class SolutionApp {

    public static int[] twoSum(int[] nums,int target){

        for (int i=0;i<nums.length-1;i++){
            int  val1 = nums[i];
            for (int j=i+1;j<nums.length;j++){
                if (nums[j] + val1 == target){
                    System.out.println(i + "," + j);
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] ints = new int[]{2,7,11,15};
        int target = 9;
        twoSum(ints,target);
    }



}
