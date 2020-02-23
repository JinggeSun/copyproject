package com.sun.nss.config;

import com.sun.nss.common.constants.RabbitMqConstants;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitmq配置
 * @author zcm
 */
@Configuration
public class RabbitMqConfig {

    /**
     * 消息队列
     * @return
     */
    @Bean
    public Queue queue(){
        return new Queue(RabbitMqConstants.REFRESH_INDEX_QUEUE);
    }
}
