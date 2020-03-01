package com.sun.task.controller;

import com.sun.task.entity.SysJobPO;
import com.sun.task.enums.SchedulingEnums;
import com.sun.task.register.CronRegistrar;
import com.sun.task.service.SysJobService;
import com.sun.task.task.SchedulingRunnable;
import javafx.print.PrinterJob;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zcm
 */
@RestController
@Slf4j
public class SysJobController {

    @Autowired
    private  SysJobService sysJobService;
    @Autowired
    private CronRegistrar cronRegistrar;

    @PostMapping("/save")
    public String save(@RequestBody SysJobPO sysJobPO){
        boolean flag =  sysJobService.save(sysJobPO);
        if (!flag){
            return "failure";
        }

        //将任务进行注册
        if (sysJobPO.getJobStatus().equals(SchedulingEnums.NORMAL.getCode())){
            SchedulingRunnable schedulingRunnable = new SchedulingRunnable(sysJobPO.getBeanName(),sysJobPO.getMethodName(),sysJobPO.getMethodParams());
            cronRegistrar.addCornCornTask(schedulingRunnable, sysJobPO.getCronExpression());
        }

        log.info("定时任务加载完毕...");

        return "success";
    }

    @GetMapping("/list")
    public List<SysJobPO> list(){
        return sysJobService.list();
    }

    @PutMapping("/update")
    public String update(@RequestBody SysJobPO sysJobPO){
        boolean flag = sysJobService.updateJob(sysJobPO);
        if (!flag){
            return "failure";
        }
        //先移除再添加
        SysJobPO exitSysJobPO = sysJobService.getById(sysJobPO.getId());
        if (exitSysJobPO.getJobStatus().equals(SchedulingEnums.NORMAL.getCode())){
            SchedulingRunnable task = new SchedulingRunnable(exitSysJobPO.getBeanName(),exitSysJobPO.getMethodName(),exitSysJobPO.getMethodParams());
            //从容器里面移除
            cronRegistrar.removerCronTask(task);
        }

        return "success";
    }

    @GetMapping("/updateStatus")
    public String taskStatus(Integer id){
        SysJobPO exitSysJob = sysJobService.getById(id);
        //去数据更改状态
        if (exitSysJob.getJobStatus().equals(SchedulingEnums.NORMAL.getCode())){
            exitSysJob.setJobStatus(SchedulingEnums.STOP.getCode());
            sysJobService.updateJob(exitSysJob);
            //正常,更改状态为移除
            SchedulingRunnable task = new SchedulingRunnable(exitSysJob.getBeanName(),exitSysJob.getMethodName(),exitSysJob.getMethodParams());
            cronRegistrar.removerCronTask(task);
            return "success";
        }else {
            exitSysJob.setJobStatus(SchedulingEnums.NORMAL.getCode());
            sysJobService.updateJob(exitSysJob);
            //暂停，改为启动
            SchedulingRunnable t = new SchedulingRunnable(exitSysJob.getBeanName(),exitSysJob.getMethodName(),exitSysJob.getMethodParams());
            cronRegistrar.addCornCornTask(t,exitSysJob.getCronExpression());
            return "success";
        }
    }

}
