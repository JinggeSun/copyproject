package com.sun.learn.realm;

import com.sun.learn.entity.Account;
import com.sun.learn.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义realm
 * @author zcm
 */
@Slf4j
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private AccountService accountService;

    /**
     * 授权 权限
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("执行授权逻辑...");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        info.addStringPermission("account:/page/account");

        //动态授权
        //Subject subject = SecurityUtils.getSubject();
        //Account account = (Account) subject.getPrincipal();
        //调用数据库，获取权限
        //info.addStringPermission(XXX);
        return info;
    }

    /**
     *  认证 用户
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("执行认证逻辑...");
        //假设数据库的用户名和密码
        //String name = "admin";
        //String password = "123456";


        //编写shiro认证逻辑，判断用户名密码
        //1 用户名
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        System.out.println("页面用户名:" + token.getUsername());

        Account account =  accountService.findByName(token.getUsername());

        if (account == null){
            //用户名不存在
            //shiro 跑出 noaccount异常
            return null;
        }
        return new SimpleAuthenticationInfo(account,account.getPassword(),"");
    }
}
