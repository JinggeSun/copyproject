package com.sun.task.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RAMJob implements Job {

    private Logger log = LoggerFactory.getLogger(RAMJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("我也不知道这个啥时候执行，反正这个是Job 的实现类");

    }
}
