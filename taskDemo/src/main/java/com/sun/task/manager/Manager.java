package com.sun.task.manager;

import com.sun.task.job.RAMJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Manager {

    public static void main(String[] args) throws Exception{
        // 创建一个JobDetail实例与HelloJob类绑定
        JobDetail jobDetail = JobBuilder.newJob(RAMJob.class).withIdentity("myJob", "group1").build();
        JobDetail jobDetail1 = JobBuilder.newJob(RAMJob.class).withIdentity("myJob1", "group2").build();
        // 创建一个Trigger实例，定义该Job立即执行，且每隔两秒钟执行一次直到永远
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("mtTrigger", "group1").startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).repeatForever()).build();
        Trigger trigger1 = TriggerBuilder.newTrigger().withIdentity("mtTrigger1", "group2").startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).repeatForever()).build();
        //创建scheduler实例
        SchedulerFactory sFactory = new StdSchedulerFactory();
        Scheduler scheduler = sFactory.getScheduler();
        scheduler.start();
        //打印当前时间
        Date startTime = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("打印当前时间:"+sf.format(startTime));
        scheduler.scheduleJob(jobDetail,trigger);
        scheduler.scheduleJob(jobDetail1,trigger1);

    }
}
