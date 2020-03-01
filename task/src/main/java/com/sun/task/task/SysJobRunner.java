package com.sun.task.task;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sun.task.entity.SysJobPO;
import com.sun.task.enums.SchedulingEnums;
import com.sun.task.register.CronRegistrar;
import com.sun.task.service.SysJobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 调度启动类
 * @author zcm
 */
//@Component
@Slf4j
public class SysJobRunner implements CommandLineRunner {

    @Autowired
    SysJobService sysJobService;

    @Autowired
    CronRegistrar cronRegistrar;

    @Override
    public void run(String... args) throws Exception {
        log.info("启动加载任务中....");
        //初始化数据库里的正常的任务
        QueryWrapper<SysJobPO> queryMapper = new QueryWrapper<>();
        queryMapper.eq("job_status", SchedulingEnums.NORMAL.getCode());
        List<SysJobPO> list = sysJobService.list(queryMapper);
        if (list.isEmpty()){
            log.info("无任务");
        }
        list.forEach(sysJobPO -> {
            log.info("加载任务：{}",sysJobPO.getBeanName());
            SchedulingRunnable schedulingRunnable = new SchedulingRunnable(sysJobPO.getBeanName(),sysJobPO.getMethodName(),sysJobPO.getMethodParams());
            cronRegistrar.addCornCornTask(schedulingRunnable,sysJobPO.getCronExpression());
        });
        log.info("加载完毕。。。");
    }
}
