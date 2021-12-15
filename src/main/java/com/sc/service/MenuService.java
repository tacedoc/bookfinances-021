package com.sc.service;

import com.sc.model.Menu;

import java.util.List;


public interface MenuService {

    /**
     * 根据菜单ID，查询菜单
     * @param id 菜单ID
     * @return
     */
    Menu queryById(Integer id);

    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     */
    List<Menu> queryListParentId(Integer parentId);

    /**
     * 获取角色菜单列表
     */
    List<Menu> getMenuList(Integer roleId);

    /**
     * 获取当前菜单，角色拥有的按钮
     * @param loginId 账号ID
     * @param url 请求
     * @return
     */
    List<Menu> getButtonList(Integer loginId,String url);

    Menu queryByUrl(String url);
}
