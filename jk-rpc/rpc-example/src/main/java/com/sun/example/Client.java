package com.sun.example;

import com.sun.rpc.client.RpcClient;

/**
 * @author zcm
 */
public class Client {

    public static void main(String[] args) throws IllegalAccessException {
        RpcClient rpcClient = new RpcClient();
        CalcService calcService = (CalcService) rpcClient.getProxy(CalcService.class);

        System.out.println(calcService.add(777,2));
        System.out.println(calcService.minus(2999,1));
    }
}
