package com.sun.simple.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 客户端代理
 * @author zcm
 */
public class ClientProxy {

    public static <T> T getRemoteProxy(final Class<T> serviceInterface, final InetSocketAddress addr){

       return (T) Proxy.newProxyInstance(serviceInterface.getClassLoader(),new Class<?>[]{serviceInterface},new MyHandler(serviceInterface,addr));

    }

    private static class MyHandler implements InvocationHandler{

        InetSocketAddress inetSocketAddress;
        Class serviceInterface;
        public MyHandler(Class serviceInterface,InetSocketAddress inetSocketAddress){
            this.inetSocketAddress = inetSocketAddress;
            this.serviceInterface = serviceInterface;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            Socket socket = null;
            ObjectOutputStream outputStream = null;
            ObjectInputStream inputStream = null;

            try {
                //创建socket客户端，制定socket服务
                socket = new Socket();
                socket.connect(inetSocketAddress);

                //将远程服务调用所需的接口类，方法名，参数列表发送给服务提供者
                outputStream = new ObjectOutputStream(socket.getOutputStream());
                outputStream.writeUTF(serviceInterface.getName());
                outputStream.writeUTF(method.getName());
                outputStream.writeObject(method.getParameterTypes());
                outputStream.writeObject(args);

                //等待
                inputStream = new ObjectInputStream(socket.getInputStream());
                return inputStream.readObject();

            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if (socket != null) {
                    socket.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            }

            return null;
        }
    }
}
