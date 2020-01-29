package com.sun.learn.service;

import com.sun.learn.entity.User;
import com.sun.learn.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zcm
 */
@Service
public class UserService {

    @Autowired
    UserRepo userRepo;


    /**
     *  @Cacheable 应用到读取数据的方法上，先从缓存中读取，如果没有再从DB获取数据，然后把数据添加到缓存中
     *            key 缓存在redis中的key
     *            unless 表示条件表达式成立的话不放入缓存
\     * @return
     */
    @Cacheable(value = "user", key = "'user_id_", unless = "#result eq null ")
    public User list() {
        return userRepo.selectList(null).get(0);
    }
}
