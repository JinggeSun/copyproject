package com.sun.water.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * @author zcm
 */
@Configuration
public class WaterFilter implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {
        //获取serverHttpRequest
        ServerHttpRequest serverHttpRequest = serverWebExchange.getRequest();
        System.out.println(serverHttpRequest.getPath().value());
        return webFilterChain.filter(serverWebExchange);
    }
}
