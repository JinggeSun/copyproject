package com.sun.app;

import java.io.File;

public class FileUtils {

    static void file(String path){
        File file = new File(path);
        System.out.println(file.length());
    }

    public static void main(String[] args) {
        file("/Users/zcm/Desktop/");
    }
}
