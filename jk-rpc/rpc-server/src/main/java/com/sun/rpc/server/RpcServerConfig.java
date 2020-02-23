package com.sun.rpc.server;

import com.sun.rpc.codec.Decoder;
import com.sun.rpc.codec.Encoder;
import com.sun.rpc.codec.JSONDecoder;
import com.sun.rpc.codec.JSONEncoder;
import com.sun.rpc.transport.HttpTransportClient;
import com.sun.rpc.transport.HttpTransportServer;
import com.sun.rpc.transport.TransportServer;
import lombok.Data;

/**
 * server配置
 * 用户使用时，要做的配置
 * 赋值：有默认的值
 * @author zcm
 */
@Data
public class RpcServerConfig {
    private Class<? extends TransportServer> transportClass = HttpTransportServer.class;
    private Class<? extends Encoder> encoderClass = JSONEncoder.class;
    private Class<? extends Decoder>  decoderClass = JSONDecoder.class;
    private int port = 3000;
}
