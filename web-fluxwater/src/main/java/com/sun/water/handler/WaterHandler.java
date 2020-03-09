package com.sun.water.handler;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * @author zcm
 */
@Component
public class WaterHandler {

    public Mono<ServerResponse> initData(ServerRequest request)  {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_STREAM_JSON).header("Content-Type",MediaType.APPLICATION_STREAM_JSON_VALUE)
                .body(BodyInserters.fromValue("SSS打发的身份"));
    }

}
