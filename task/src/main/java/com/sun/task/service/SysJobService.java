package com.sun.task.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sun.task.entity.SysJobPO;
import org.springframework.stereotype.Service;

/**
 * @author zcm
 */
public interface SysJobService extends IService<SysJobPO> {
    /**
     * 更新job
     * @param sysJobPO
     * @return
     */
    boolean updateJob(SysJobPO sysJobPO);

}
