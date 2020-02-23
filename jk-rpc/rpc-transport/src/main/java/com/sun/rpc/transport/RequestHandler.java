package com.sun.rpc.transport;

import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author zcm
 */
public interface RequestHandler {

    /**
     * 请求
     * @param receive
     * @param toResp
     */
    void onRequest(InputStream receive, OutputStream toResp);
}
