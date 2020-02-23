package com.sun.nss.common.base;

import com.sun.nss.entity.sys.SysUser;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 控制器基类
 * @author zcm
 */
public class AbstractController {


    /**
     * 从shiro中取出用户信息
     * @return
     */
    protected SysUser getUser(){
        return (SysUser) SecurityUtils.getSubject().getPrincipal();
    }

    protected Integer getUserId(){
        return getUser().getUserId();
    }
}
