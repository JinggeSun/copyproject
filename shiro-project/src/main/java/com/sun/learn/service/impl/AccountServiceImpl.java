package com.sun.learn.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.learn.entity.Account;
import com.sun.learn.mapper.AccountMapper;
import com.sun.learn.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zcm
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    private final AccountMapper accountMapper;

    @Override
    public Account findByName(String userName) {
        QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",userName);
        return accountMapper.selectList(queryWrapper).get(0);
    }
}

