package com.sun.learnkafka.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author zcm
 */
@Component
public class MyKafkaListener {

    Logger logger = LoggerFactory.getLogger(MyKafkaListener.class.getName());

    @KafkaListener(topics = "big",id = "mygroup")
    public void listen(String input){
        logger.info("input value :{}",input);
    }
}
