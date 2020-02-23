package com.sun.rpc.client;

import com.sun.rpc.Request;
import com.sun.rpc.Response;
import com.sun.rpc.ServiceDescriptor;
import com.sun.rpc.codec.Decoder;
import com.sun.rpc.codec.Encoder;
import com.sun.rpc.transport.TransportClient;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
/**
 * 调用远程服务的代理类
 * @author zcm
 */
public class RemoteInvoker implements InvocationHandler {

    private Class clazz;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector transportSelector;

    RemoteInvoker(Class clazz, Encoder encoder, Decoder decoder,TransportSelector transportSelector){
        this.encoder = encoder;
        this.decoder = decoder;
        this.transportSelector = transportSelector;
        this.clazz = clazz;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {

        Request request = new Request();
        request.setServiceDescriptor(ServiceDescriptor.from(clazz,method));
        request.setParameters(args);

        Response response = invokeRemote(request);
        if (response.getCode() != 0){
            throw new IllegalStateException("fffffffail");
        }
        return response.getData();
    }

    private Response invokeRemote(Request request) {
        Response response = null;
        TransportClient transportClient = null;
        try{
            transportClient = transportSelector.select();
            byte[] outBytes = encoder.encoder(request);
            InputStream receive =  transportClient.write(new ByteArrayInputStream(outBytes));

            byte[] inBytes = IOUtils.readFully(receive,receive.available());
            response = decoder.decoder(inBytes,Response.class);

        }catch (Exception e){
            response = new Response();
            response.setCode(-1);
            response.setMessage("error"+e.getMessage());
        }finally {
            if (transportClient != null){
                transportClient.close();
            }
        }
        return response;
    }
}
