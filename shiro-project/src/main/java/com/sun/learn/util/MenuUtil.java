package com.sun.learn.util;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.sun.learn.entity.SysMenu;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 菜单工具类，生成菜单的其他信息。
 * @author zcm
 */
public class MenuUtil {

    /**
     * 获取菜单的基本情况
     * @return
     */
    public static Map<String,Object> getMenuMap(){
          Map<String,Object> map = new HashMap<>();
          // 清楚缓存
        Map<String,String> cacheMap = new HashMap<>();
        cacheMap.put("clearUrl","api/clear.json");
        map.put("clearInfo",cacheMap);
        //首页信息
        Map<String,String> homeMap = new HashMap<>();
        homeMap.put("title","首页");
        homeMap.put("icon","fa fa-home");
        homeMap.put("href","/page/home");
        map.put("homeInfo",homeMap);
        //logo信息
        Map<String,String> logoMap = new HashMap<>();
        logoMap.put("title","GrapeFruit");
        logoMap.put("image","/public/images/logo.png");
        logoMap.put("href","");
        map.put("logoInfo",logoMap);

        return map;
    }


    /**
     * 初始化菜单树
     * @param sysMenuList
     * @return
     */
    private static Map<String,Object> initMenuTree(List<SysMenu> sysMenuList){

        Map<String,Object> map = new HashMap<>();

        //1. 查找根节点
        List<SysMenu> rootMenu = new ArrayList<>();
        sysMenuList.forEach(sysMenu -> {
            if (sysMenu.getPId() == 0){
                rootMenu.add(sysMenu);
            }
        });

        //2. 设置child
        rootMenu.forEach(sysMenu -> {
            Integer id = sysMenu.getId();
            sysMenu.setChild(getChild(id,sysMenuList));
        });

        rootMenu.forEach(sysMenu -> {
            map.put(sysMenu.getInfo(),sysMenu);
        });
        return map;
    }


    public static Map<String,Object> initMenu(List<SysMenu> menuList){

        //基础
        Map<String,Object> menuMap = new HashMap<>();
        menuMap = getMenuMap();
        //菜单

        Map<String,Object> sysMenus = initMenuTree(menuList);

        menuMap.put("menuInfo",sysMenus);

        return menuMap;
    }

    private static List<SysMenu> getChild(Integer id, List<SysMenu> allMenu) {
        List<SysMenu> childMenuList = new ArrayList<>();

        allMenu.forEach(sysMenu -> {
            if (sysMenu.getPId() !=0 && sysMenu.getPId().equals(id)){
                childMenuList.add(sysMenu);
            }
        });

        childMenuList.forEach(sysMenu -> {
            if (StringUtils.isEmpty(sysMenu.getHref())){
                sysMenu.setChild(getChild(sysMenu.getId(),allMenu));
            }
        });

        if (childMenuList.size() == 0){
            return null;
        }

        return childMenuList;

    };


    public static void main(String[] args) {

        System.out.println(getMenuMap());

        List<SysMenu> sysMenus = new ArrayList<>();

        SysMenu sysMenu = new SysMenu();
        sysMenu.setId(1);
        sysMenu.setPId(0);
        sysMenu.setTitle("跟菜单");

        SysMenu sysMenu1 = new SysMenu();
        sysMenu1.setId(2);
        sysMenu1.setPId(1);
        sysMenu1.setTitle("主菜单");


        SysMenu sysMenu2 = new SysMenu();
        sysMenu2.setId(3);
        sysMenu2.setPId(2);
        sysMenu2.setTitle("子菜单");
        sysMenu2.setHref("/menu2");

        SysMenu sysMenu3 = new SysMenu();
        sysMenu3.setId(4);
        sysMenu3.setPId(2);
        sysMenu3.setTitle("子菜单");
        sysMenu3.setHref("/menu3");

        sysMenus.add(sysMenu);
        sysMenus.add(sysMenu1);
        sysMenus.add(sysMenu2);
        sysMenus.add(sysMenu3);

        Map<String,Object> map = Maps.newHashMap();

        map = initMenu(sysMenus);

        System.out.println(JSON.toJSON(map));

    }

}
