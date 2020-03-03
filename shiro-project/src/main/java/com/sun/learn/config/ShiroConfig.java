package com.sun.learn.config;

import com.sun.learn.realm.CustomRealm;
import com.sun.learn.util.ShiroUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro 配置
 * 3大核心组件：subject，security manager ， realm
 *  认证中体
 *  安全管理员
 *  域
 * @author zcm
 */
@Configuration
@Slf4j
public class ShiroConfig {

    /**
     * 创建shirofilterfactorybean
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();

        //关联安全管理
        filterFactoryBean.setSecurityManager(securityManager);

        /**
         * 过滤器
         * anon: 无需认证（登陆），可以访问
         * authc: 必须认证才能访问
         * user: 如果使用remember me的功能可以直接访问
         * perms： 该资源必须得到资源权限才能访问
         * role: 必须得到角色权限才能访问
         */

        // 按照顺序的map
        Map<String,String> filterMap = new LinkedHashMap<>();

        //登陆提交接口
        filterMap.put("/login","anon");
        filterMap.put("/getCaptcha","anon");
        //静态资源
        filterMap.put("/public/**","anon");

        //授权过滤器
        //访问这个接口，必须授权。在realm进行授权
        //filterMap.put("/page/account","perms[account:/page/account]");

        filterMap.put("/**","authc");

        filterFactoryBean.setFilterChainDefinitionMap(filterMap);
        //配置登陆页面
        filterFactoryBean.setLoginUrl("/page/toLogin");
        //未授权页面
        filterFactoryBean.setUnauthorizedUrl("/page/noauth");

        return filterFactoryBean;
    }

    /**
     * 创建defaultwebsecuritymanager
     * @return
     */
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 关联realm
        securityManager.setRealm(customRealm(matcher()));
        return securityManager;
    }

    /**
     * 创建realm
     * 1. 加入加密算法
     */
    @Bean
    public Realm customRealm(HashedCredentialsMatcher matcher){
        log.info("自定义realm 注册完成！");
        CustomRealm customRealm = new CustomRealm();
        //realm 关联算法
        customRealm.setCredentialsMatcher(matcher);
        return customRealm;
    }

    /**
     * 加密配置
     * @return
     */
    @Bean
    public HashedCredentialsMatcher matcher(){
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        //加密算法
        matcher.setHashAlgorithmName(ShiroUtil.ALG_NAME);
        //加密次数
        matcher.setHashIterations(ShiroUtil.HASH_ITERATION);
        return matcher;
    }



}
