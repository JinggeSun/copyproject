package com.sun.rpc.client;

import com.sun.rpc.codec.Decoder;
import com.sun.rpc.codec.Encoder;
import com.sun.rpc.common.utils.ReflectionsUtils;
import lombok.Data;

import java.lang.reflect.Proxy;

@Data
public class RpcClient {

    private RpcClientConfig rpcClientConfig;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector transportSelector;

    public RpcClient() throws IllegalAccessException {
        this(new RpcClientConfig());
    }

    public RpcClient(RpcClientConfig rpcClientConfig) throws IllegalAccessException {
        this.rpcClientConfig = rpcClientConfig;

        this.encoder = ReflectionsUtils.newInstance(this.rpcClientConfig.getEncoder());

        this.decoder = ReflectionsUtils.newInstance(this.rpcClientConfig.getDecoder());

        this.transportSelector = ReflectionsUtils.newInstance(this.rpcClientConfig.getTransportSelector());

        assert transportSelector != null;
        transportSelector.init(
                this.rpcClientConfig.getServers(),
                this.rpcClientConfig.getConnectCount(),
                this.rpcClientConfig.getTransportClient()
        );
    }

    public <T> Object getProxy(Class<T> clazz){
        return Proxy.newProxyInstance(getClass().getClassLoader(),new Class[]{clazz},new RemoteInvoker(clazz,encoder,decoder,transportSelector));
    }

}
