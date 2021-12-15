package com.sc.dao.impl;

import com.sc.dao.MenuDao;
import com.sc.model.Menu;
import com.sc.util.DBUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class MenuDaoImpl implements MenuDao {

    @Override
    public Menu queryById(Integer id) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select id,parent_id as parentId,name,menu_type as menuType,url,icon,menu_sort as menuSort from menu where id=?";
        Object[] params = {id};
        Menu menu = null;
        menu = queryRunner.query(DBUtil.getThreadCon(), sql, new BeanHandler<>(Menu.class), params);
        return menu;
    }

    @Override
    public List<Menu> queryListParentId(Integer parentId) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select id,parent_id as parentId,name,menu_type as menuType,url,icon,menu_sort as menuSort " +
                "from menu " +
                "where parent_id=? " +
                "order by menu_sort";
        Object[] params = {parentId};
        List<Menu> list = null;
        list = queryRunner.query(DBUtil.getThreadCon(), sql, new BeanListHandler<>(Menu.class), params);
        return list;
    }

    /**
     * 根据角色ID，查询菜单ID
     * @param roleId 菜单ID
     * @return
     * @throws SQLException
     */
    @Override
    public List<Menu> queryListRoleId(Integer roleId) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select b.id,b.parent_id as parentId,b.name,b.menu_type as menuType,b.url,b.icon,b.menu_sort as menuSort " +
                "from role_menu a " +
                "left join menu b " +
                "on a.menu_id=b.id " +
                "where a.role_id=? " +
                "order by menu_sort";
        Object[] params = {roleId};
        List<Menu> list = null;
        list = queryRunner.query(DBUtil.getThreadCon(), sql, new BeanListHandler<>(Menu.class), params);
        return list;
    }

    /**
     * 根据url取得菜单
     * @param url
     * @return
     */
    @Override
    public Menu queryByUrl(String url) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select id,parent_id as parentId,name,menu_type as menuType,url,icon,menu_sort as menuSort " +
                "from menu " +
                "where url=?";
        Object[] params = {url};
        Menu menu = null;
        menu = queryRunner.query(DBUtil.getThreadCon(), sql, new BeanHandler<>(Menu.class), params);
        return menu;
    }
}
