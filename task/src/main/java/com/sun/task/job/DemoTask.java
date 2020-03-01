package com.sun.task.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author zcm
 */
@Component(value = "demoTask")
@Slf4j
public class DemoTask {

    public void taskWithParams(String params){
        log.info("正在运行：有参数的示例任务。参数" + params +"。"+ LocalDateTime.now());
    }


    public void taskNoParams(){
        log.info("正在运行：无参数的示例任务。" + LocalDateTime.now());
    }

}
