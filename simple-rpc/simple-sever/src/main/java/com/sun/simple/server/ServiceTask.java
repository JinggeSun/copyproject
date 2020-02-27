package com.sun.simple.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * @author zcm
 */
public class ServiceTask implements Runnable {

    Socket client = null;

    public ServiceTask(Socket accept) {
        this.client = accept;
    }

    @Override
    public void run() {

        //定义2个流
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;
        try {
            //从socket获取输入流
            inputStream = new ObjectInputStream(client.getInputStream());
            //序列化
            String serviceName = inputStream.readUTF();
            String methodName = inputStream.readUTF();

            //获取输入参数的类型
            Class<?>[] parameterTypes = (Class<?>[]) inputStream.readObject();
            //获取输入的参数
            Object[] arguments = (Object[]) inputStream.readObject();
            //获取实现类
            Class serviceClass = ServerCenter.serviceRegistry.get(serviceName);
            if (serviceClass == null){
                throw new ClassNotFoundException(serviceName+"not fount");
            }

            // 根据方法名和参数类型获取方法
            Method method = serviceClass.getMethod(methodName,parameterTypes);
            Object result = method.invoke(serviceClass.newInstance(),arguments);

            outputStream = new ObjectOutputStream(client.getOutputStream());
            outputStream.writeObject(result);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (outputStream != null){
                try {
                    outputStream.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if (inputStream != null){
                try {
                    inputStream.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if (client != null){
                try {
                    client.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
