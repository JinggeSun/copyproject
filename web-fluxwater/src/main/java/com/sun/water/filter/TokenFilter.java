package com.sun.water.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * @author zcm
 */
@Configuration
public class TokenFilter implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {
        //获取serverHttpRequest
        ServerHttpRequest serverHttpRequest = serverWebExchange.getRequest();

        Optional<String> token  = Optional.ofNullable(serverHttpRequest.getHeaders().getFirst("token"));

        if (token.isPresent()){
            //跑出异常
            throw new RuntimeException("no token");
        }else{
            //验证token
            System.out.println("ok");
        }

        return webFilterChain.filter(serverWebExchange);
    }
}
