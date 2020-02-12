package com.sun.mini.core;

import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassScanner {

    public static List<Class<?>> scannerClasses(String packageName) throws IOException, ClassNotFoundException {

        List<Class<?>> classList = new ArrayList<>();

        //1. 转换路径
        String path = packageName.replace(".","/");
        System.out.println(path);
        //2. classloader
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Enumeration<URL> enumeration =  classLoader.getResources(path);
        //3. 遍历
        while (enumeration.hasMoreElements()){
            // 获取资源
            URL resource = enumeration.nextElement();
            // 判断是否是jar
            if (resource.getProtocol().contains("jar")){
                JarURLConnection jarURLConnection = (JarURLConnection) resource.openConnection();
                // 获取jar包路径
                String jarFilePaht = jarURLConnection.getJarFile().getName();
                classList.addAll(getClassesFromJar(jarFilePaht,path));
            }
        }
        return classList;
    }
    private static List<Class<?>> getClassesFromJar(String jarFilePath, String path) throws IOException, ClassNotFoundException {

        List<Class<?>> classList = new ArrayList<>();
        //1. 文件
        JarFile jarFile = new JarFile(jarFilePath);

        Enumeration<JarEntry> jarEntryEnumeration = jarFile.entries();

        while (jarEntryEnumeration.hasMoreElements()){
            JarEntry jarEntry = jarEntryEnumeration.nextElement();
            //com/sun.mytest.XXX.class
            String entityName = jarEntry.getName();
            if (entityName.startsWith(path) && entityName.endsWith("class")) {
                String classFullName = entityName.replace("/", ".").substring(0, entityName.length() - 6);
                classList.add(Class.forName(classFullName));
            }
        }

        return classList;
    }
}
