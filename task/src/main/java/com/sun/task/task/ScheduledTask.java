package com.sun.task.task;

import java.util.concurrent.ScheduledFuture;

/**
 * 调度作业
 * @author zcm
 */
public class ScheduledTask {

    public ScheduledFuture<?> future;
    /**
     * 取消
     */
    public void cancel(){
        ScheduledFuture<?> future = this.future;
        if (future != null){
            future.cancel(true);
        }
    }
}
