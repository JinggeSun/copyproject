package com.sun.water.router;

import com.sun.water.filter.WaterFilter;
import com.sun.water.handler.WaterHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

/**
 * @author zcm
 */
@Configuration
public class WaterRouter {

    @Bean
    public RouterFunction<ServerResponse> helloWater(WaterHandler waterHandler) {
        return RouterFunctions.route(RequestPredicates
                        .GET("/hello")
                        .and(RequestPredicates.accept(MediaType.TEXT_PLAIN))
                , waterHandler::initData);
    }


}
