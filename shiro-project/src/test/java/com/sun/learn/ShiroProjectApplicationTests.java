package com.sun.learn;

import com.alibaba.fastjson.JSON;
import com.sun.learn.entity.SysMenu;
import com.sun.learn.mapper.SysMenuMapper;
import com.sun.learn.service.AccountService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.security.RunAs;
import javax.swing.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class ShiroProjectApplicationTests {

	@Autowired
	AccountService accountService;

	@Autowired(required = false)
	SysMenuMapper sysMenuMapper;

	@Test
	void contextLoads() {

		//System.out.println(sysMenuMapper.selectList(null));

		//System.out.println(accountService.list());

		System.out.println(JSON.toJSON(accountService.findMenuById(1)));

	}

	public static void main(String[] args) {
		String salt = new SecureRandomNumberGenerator().nextBytes().toString();
		System.out.println(salt);
		SimpleHash hash = new SimpleHash("md5", "123456", salt,2);
		System.out.println(hash);
	}

}
