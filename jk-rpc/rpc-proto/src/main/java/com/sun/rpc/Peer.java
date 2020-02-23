package com.sun.rpc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 表示网络传输的一个端点
 *
 * @author zcm
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Peer {
    /**
     * 服务器的host地址
     */
    private String host;
    /**
     * 服务器的端口
     */
    private int port;
}
