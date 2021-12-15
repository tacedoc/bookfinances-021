package com.sc.dao;

import com.sc.model.Menu;

import java.sql.SQLException;
import java.util.List;

public interface MenuDao {

    /**
     * 根据菜单ID，查询菜单
     * @param id 菜单ID
     * @return
     */
    Menu queryById(Integer id) throws SQLException;

    /**
     * 根据父菜单ID，查询子菜单
     * @param parentId 父菜单ID
     */
    List<Menu> queryListParentId(Integer parentId) throws SQLException;

    /**
     * 根据角色ID，查询菜单
     * @param roleId 菜单ID
     * @return
     * @throws SQLException
     */
    List<Menu> queryListRoleId(Integer roleId) throws SQLException;

    /**
     * 根据url取得菜单
     * @param url
     * @return
     */
    Menu queryByUrl(String url) throws SQLException;

}
