package com.sun.task.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.task.entity.SysJobPO;
import com.sun.task.mapper.SysJobMapper;
import com.sun.task.service.SysJobService;
import org.springframework.stereotype.Service;

/**
 * @author zcm
 */
@Service
public class SysJobServiceImpl extends ServiceImpl<SysJobMapper, SysJobPO> implements SysJobService {
    @Override
    public boolean updateJob(SysJobPO sysJobPO) {
        UpdateWrapper<SysJobPO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",sysJobPO.getId());
        return this.update(sysJobPO,updateWrapper);
    }

}
