package com.sun.learnkafka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zcm
 */
@RestController
public class IndexController {

    Logger logger = LoggerFactory.getLogger(IndexController.class.getName());

    @Autowired
    KafkaTemplate<Object,Object> kafkaTemplate;


    @GetMapping("/send/{input}")
    public String send(@PathVariable String input){
        logger.info(input);
        kafkaTemplate.send("big",input);
        return "success";
    }

}
