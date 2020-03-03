package com.sun.learn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sun.learn.entity.Account;

import java.util.Map;

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

    /**
     * 根据id获取菜单信息
     * @param id
     * @return
     */
    Map<String,Object> findMenuById(Integer id);
}
