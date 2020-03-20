package com.sun.mp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sun.mp.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户Mapper层
 * @author nieqiurong 2018-08-10 22:54:51.
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
