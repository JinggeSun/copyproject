package com.sun.learn;

import com.sun.learn.entity.User;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @org.junit.Test
    public void redisTest(){
       // redisTemplate.opsForValue().set("mac","zcm");

        System.out.println(redisTemplate.opsForValue().get("mac"));

        User user = User.builder().id(1).age(10).gender(1).name("33").build();

        redisTemplate.opsForValue().set("pc",user);

        User user1 = (User) redisTemplate.opsForValue().get("pc");

        assert user1 != null;
        System.out.println(user1.toString());
    }

}
