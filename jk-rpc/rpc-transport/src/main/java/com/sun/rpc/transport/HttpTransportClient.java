package com.sun.rpc.transport;

import com.sun.rpc.Peer;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * http 短连接，无需关闭
 * @author zcm
 */
public class HttpTransportClient implements TransportClient{

    private String url;

    /**
     * 组合http连接
     * @param peer
     */
    @Override
    public void connect(Peer peer) {
        this.url = "http://" + peer.getHost() +":" + peer.getPort();
    }

    @Override
    public void close() {

    }
    /**
     * 使用http url connection连接
     *
     * @param data
     * @return
     */
    @Override
    public InputStream write(InputStream data) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setUseCaches(true);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.connect();
            IOUtils.copy(data,httpURLConnection.getOutputStream());
            int resultCode = httpURLConnection.getResponseCode();
            if (resultCode == HttpURLConnection.HTTP_OK){
                 return httpURLConnection.getInputStream();
            }else {
                return httpURLConnection.getErrorStream();
            }
        }catch (Exception e){
            throw new IllegalStateException(e);
        }
    }
}
