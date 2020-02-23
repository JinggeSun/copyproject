package com.sun.rpc.client;

import com.sun.rpc.Peer;
import com.sun.rpc.common.utils.ReflectionsUtils;
import com.sun.rpc.transport.TransportClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author zcm
 */
public class RandomTransportSelector implements TransportSelector {

    private List<TransportClient> clients;

    public RandomTransportSelector(){
        this.clients = new ArrayList<>();
    }

    @Override
    public synchronized TransportClient select() {
        int i = new Random().nextInt(clients.size());
        return clients.remove(i);
    }

    @Override
    public synchronized void release(TransportClient client) {
        clients.add(client);
    }

    @Override
    public synchronized void close() {
        clients.forEach(TransportClient::close);
        clients.clear();
    }

    @Override
    public synchronized void init(List<Peer> peerList, int count, Class<? extends TransportClient> clazz) throws IllegalAccessException {
       // 比大小
        count = Math.max(count,1);

        for (Peer peer : peerList){
            for (int i=0;i<count;i++){
                TransportClient transportClient = ReflectionsUtils.newInstance(clazz);
                assert transportClient != null;
                transportClient.connect(peer);
                clients.add(transportClient);
            }
        }
    }
}
