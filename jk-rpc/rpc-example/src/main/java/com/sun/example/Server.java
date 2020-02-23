package com.sun.example;

import com.sun.rpc.server.RpcServer;
import com.sun.rpc.server.RpcServerConfig;

/**
 * @author zcm
 */
public class Server {

    public static void main(String[] args) throws IllegalAccessException {
        RpcServer rpcServer = new RpcServer();
        rpcServer.register(CalcService.class,new CalcServiceImpl());
        rpcServer.start();
    }
}
