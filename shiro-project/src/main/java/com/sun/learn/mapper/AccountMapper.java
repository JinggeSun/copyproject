package com.sun.learn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sun.learn.entity.Account;
import com.sun.learn.entity.SysMenu;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zcm
 */
public interface AccountMapper extends BaseMapper<Account> {
    /**
     * 根据id查找菜单
     * @param id
     * @return
     */
    @Select("SELECT m.id,m.p_id,m.name as title,m.url as href,m.target,m.icon,m.info from sys_menu m JOIN sys_role_menu srm on srm.menu_id = m.id JOIN sys_role sr on sr.id = srm.role_id JOIN sys_user_role sur on sur.user_id = sur.role_id\n" +
            "where sur.user_id = ${id}\n" +
            "\n")
    List<SysMenu> findMenuById(Integer id);
}
