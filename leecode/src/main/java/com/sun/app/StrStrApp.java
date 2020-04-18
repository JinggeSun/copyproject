package com.sun.app;

public class StrStrApp {

    public static void main(String[] args) {
        System.out.println(strStr("hello","hello"));
    }

    public static int strStr(String haystack, String needle) {
        if (needle.length() == 0){
            return 0;
        }


        if (haystack.length() < needle.length()){
            return -1;
        }
        int haLength = haystack.length();
        int neLength = needle.length();


        for (int i=0;i<=haLength-neLength;i++){
            String value = haystack.substring(i,i+neLength);
            if (value.equals(needle)){
                return i;
            }
        }

        return -1;
    }
}
