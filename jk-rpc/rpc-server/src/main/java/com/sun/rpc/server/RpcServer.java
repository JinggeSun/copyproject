package com.sun.rpc.server;

import com.sun.rpc.Request;
import com.sun.rpc.Response;
import com.sun.rpc.codec.Decoder;
import com.sun.rpc.codec.Encoder;
import com.sun.rpc.common.utils.ReflectionsUtils;
import com.sun.rpc.transport.RequestHandler;
import com.sun.rpc.transport.TransportServer;
import lombok.Data;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author zcm
 */
public class RpcServer {

    /**
     * 配置
     */
    private RpcServerConfig rpcServerConfig;

    /**
     * 网络模块
     */
    private TransportServer net;

    private Encoder encoder;

    private Decoder decoder;

    private ServiceManager serviceManager;

    private ServiceInvoker serviceInvoker;

    public RpcServer() {
        this(new RpcServerConfig());
    }

    public RpcServer(RpcServerConfig rpcServerConfig) {
        this.rpcServerConfig = rpcServerConfig;
        System.out.println(rpcServerConfig.getTransportClass());
        this.net = ReflectionsUtils.newInstance(rpcServerConfig.getTransportClass());
        if (net == null){
            throw  new IllegalStateException("eeeee");
        }
        this.net.init(rpcServerConfig.getPort(), this.requestHandler);
        this.decoder = ReflectionsUtils.newInstance(rpcServerConfig.getDecoderClass());
        this.encoder = ReflectionsUtils.newInstance(rpcServerConfig.getEncoderClass());
        this.serviceManager = new ServiceManager();
        this.serviceInvoker = new ServiceInvoker();
    }

    public <T> void register(Class<T> interfaceClass, T bean) {
        this.serviceManager.register(interfaceClass, bean);
    }

    public void start() {
        this.net.start();
    }

    public void stop() {
        this.net.stop();
    }


    private RequestHandler requestHandler = new RequestHandler() {
        @Override
        public void onRequest(InputStream receive, OutputStream toResp) {
            Response response = new Response();
            try {
                byte[] inBytes = IOUtils.readFully(receive,receive.available());
                Request request = decoder.decoder(inBytes,Request.class);
                ServiceInstance serviceInstance = serviceManager.lookup(request);
                Object ret = serviceInvoker.invoker(serviceInstance,request);
                response.setData(ret);
            }catch (Exception e){
                response.setCode(-1);
                response.setMessage(e.getMessage());
            }finally {
                try {
                    byte[] outBytes = encoder.encoder(response);
                    toResp.write(outBytes);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    };
}
