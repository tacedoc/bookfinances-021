package com.sc.service.impl;

import com.sc.dao.MenuDao;
import com.sc.dao.impl.MenuDaoImpl;
import com.sc.model.Login;
import com.sc.model.Menu;
import com.sc.model.User;
import com.sc.service.LoginService;
import com.sc.service.MenuService;
import com.sc.service.RoleMenuService;
import com.sc.service.UserService;
import com.sc.util.DBUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuServiceImpl implements MenuService {

    private MenuDao menuDao = new MenuDaoImpl();
    private LoginService loginService = new LoginServiceImpl();
    private UserService userService = new UserServiceImpl();

    @Override
    public Menu queryById(Integer id) {
        Menu menu = null;
        try {
            menu = menuDao.queryById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeConnection();
        }
        return menu;
    }

    @Override
    public List<Menu> queryListParentId(Integer parentId) {
        List<Menu> list = null;
        try {
            list = menuDao.queryListParentId(parentId);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeConnection();
        }
        return list;
    }

    /**
     * 取出角色中的包含的子菜单
     * @param parentId
     * @param roleAllMenuList
     * @return
     */
    public List<Menu> queryListParentId(Integer parentId,List<Menu> roleAllMenuList) {
        List<Menu> list = new ArrayList<>();
        try {
            List<Menu> parentList = menuDao.queryListParentId(parentId);
            for (Menu menu : parentList) {
                if (roleAllMenuList.contains(menu)){
                    list.add(menu);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeConnection();
        }
        return list;
    }



    @Override
    public List<Menu> getMenuList(Integer roleId) {
        List<Menu> list = new ArrayList<>();
        try {
            /*拿出所有的导航菜单，再拿出角色拥有的所有菜单ID
            * 最后递归设置菜单的子菜单
            * */
            List<Menu> dirMenuList = menuDao.queryListParentId(0);
            List<Menu> menuList = menuDao.queryListRoleId(roleId);
            for (Menu menu : dirMenuList) {
                if (menuList.contains(menu)){
                   list.add(menu);
                }
            }
            getMenuTreeList(list,menuList);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeConnection();
        }
        return list;
    }

    @Override
    public List<Menu> getButtonList(Integer loginId,String url) {
        List<Menu> list = new ArrayList<>();
        try {
            Login login = loginService.queryById(loginId);
            User user = userService.queryById(login.getUserId());
            Menu menu = queryByUrl(url);
/*
            先拿出角色拥有的所有菜单ID（包括按钮）,
            再拿出当前菜单拥有的所有按钮,
            最后判定，取出当前菜单角色拥有的按钮
*/
            List<Menu> menuList = menuDao.queryListRoleId(user.getRoleId());
            List<Menu> buttonList = menuDao.queryListParentId(menu.getId());
            for (Menu button : buttonList) {
                if (menuList.contains(button)){
                    list.add(button);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeConnection();
        }
        return list;
    }

    @Override
    public Menu queryByUrl(String url) {
        Menu menu = null;
        try {
            menu = menuDao.queryByUrl(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeConnection();
        }
        return menu;
    }

    /**
     * 递归设置子菜单
     */
    private List<Menu> getMenuTreeList(List<Menu> dirMenuList, List<Menu> roleAllMenuList){
        List<Menu> subMenuList = new ArrayList<>();
        for(Menu menu : dirMenuList){
            //目录
            if(menu.getMenuType() == 1){
                menu.setChildMenuList(getMenuTreeList(queryListParentId(menu.getId(),roleAllMenuList),roleAllMenuList));
            }
            subMenuList.add(menu);
        }
        return subMenuList;
    }


}
