package com.sun.task.register;

import com.sun.task.task.ScheduledTask;
import com.sun.task.task.SchedulingRunnable;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.config.CronTask;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 定时任务注册类
 * @author zcm
 */
@Component
public class CronRegistrar implements DisposableBean {

    /**
     * 存储任务的容器
     */
    private final Map<Runnable, ScheduledTask> scheduledTaskMap = new ConcurrentHashMap<>();


    @Autowired
    private TaskScheduler taskScheduler;

    public TaskScheduler getTaskScheduler(){
        return this.taskScheduler;
    }

    public void addCornCornTask(Runnable task,String cornExpression){
        addCornCornTask(new CronTask(task,cornExpression));
        
    }

    private void addCornCornTask(CronTask cronTask) {
        if (cronTask != null){
            Runnable task = cronTask.getRunnable();
            if (this.scheduledTaskMap.containsKey(task)){
                removerCronTask(task);
            }
            this.scheduledTaskMap.put(task,scheduleCronTask(cronTask));
        }
    }

    private ScheduledTask scheduleCronTask(CronTask cronTask) {
        ScheduledTask scheduledTask = new ScheduledTask();
        scheduledTask.future = this.taskScheduler.schedule(cronTask.getRunnable(),cronTask.getTrigger());
        return scheduledTask;
    }

    public void removerCronTask(Runnable task) {
        ScheduledTask scheduledTask = this.scheduledTaskMap.remove(task);
        if (scheduledTask != null){
            scheduledTask.cancel();;
        }
    }



    @Override
    public void destroy() throws Exception {
        scheduledTaskMap.forEach((runnable, scheduledTask) -> {
            scheduledTask.cancel();
        });
        this.scheduledTaskMap.clear();
    }
}
