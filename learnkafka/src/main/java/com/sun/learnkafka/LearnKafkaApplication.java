package com.sun.learnkafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class LearnKafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnKafkaApplication.class,args);
    }

}
