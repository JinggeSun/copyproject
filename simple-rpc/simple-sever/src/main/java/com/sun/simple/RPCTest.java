package com.sun.simple;

import com.google.common.collect.Maps;
import com.sun.simple.api.UserService;
import com.sun.simple.client.ClientProxy;
import com.sun.simple.server.Server;
import com.sun.simple.server.ServerCenter;
import com.sun.simple.service.impl.UserServiceImpl;

import java.io.IOException;
import java.net.InetSocketAddress;

public class RPCTest {

    public static void main(String[] args) throws IOException {
        new Thread(()->{
            try {
                Server serviceServer = new ServerCenter(8088);
                serviceServer.register(UserService.class,UserServiceImpl.class);
                serviceServer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        UserService service = ClientProxy.getRemoteProxy(UserService.class, new InetSocketAddress("localhost", 8088));
        System.out.println(service.getUserName(1));
    }
}
