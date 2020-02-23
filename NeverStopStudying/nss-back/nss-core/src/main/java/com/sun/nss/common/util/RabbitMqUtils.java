package com.sun.nss.common.util;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 发送消息
 * @author zcm
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RabbitMqUtils {

    private final AmqpTemplate amqpTemplate;

    /**
     * 发送消息到queue
     * @param queueName
     * @param object
     */
    public void send(String queueName,Object object){
        this.amqpTemplate.convertAndSend(queueName,object);
    }
}
