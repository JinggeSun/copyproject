package com.sun.learn.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sun.learn.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author zcm
 */
@Repository
public interface UserRepo extends BaseMapper<User> {

}
