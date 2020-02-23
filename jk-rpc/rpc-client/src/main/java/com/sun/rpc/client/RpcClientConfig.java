package com.sun.rpc.client;

import com.sun.rpc.Peer;
import com.sun.rpc.codec.Decoder;
import com.sun.rpc.codec.Encoder;
import com.sun.rpc.codec.JSONDecoder;
import com.sun.rpc.codec.JSONEncoder;
import com.sun.rpc.transport.HttpTransportClient;
import com.sun.rpc.transport.TransportClient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author zcm
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RpcClientConfig {

    private Class<? extends TransportClient> transportClient = HttpTransportClient.class;

    private Class<? extends Decoder> decoder = JSONDecoder.class;

    private Class<? extends Encoder> encoder = JSONEncoder.class;

    private Class<? extends TransportSelector> transportSelector = RandomTransportSelector.class;

    private int connectCount = 3;

    private List<Peer> servers = Collections.singletonList(new Peer("127.0.0.1", 3000));

}
