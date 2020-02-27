package com.sun.learn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sun.learn.entity.Account;

/**
 * @author zcm
 */
public interface AccountService extends IService<Account> {
    /**
     * 根据用户名查找用户信息
     * @param userName
     * @return
     */
    Account findByName(String userName);
}
