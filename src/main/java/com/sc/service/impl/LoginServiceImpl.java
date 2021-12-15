package com.sc.service.impl;

import com.sc.dao.LoginDao;
import com.sc.dao.UserDao;
import com.sc.dao.impl.LoginDaoImpl;
import com.sc.dao.impl.UserDaoImpl;
import com.sc.model.Book;
import com.sc.model.Login;
import com.sc.model.TradeFlow;
import com.sc.model.User;
import com.sc.service.LoginService;
import com.sc.util.DBUtil;
import com.sc.vo.Page;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class LoginServiceImpl implements LoginService {

    private LoginDao loginDao = new LoginDaoImpl();
    private UserDao userDao = new UserDaoImpl();

    @Override
    public Boolean registry(Login login, User user) {
        Connection connection = null;
        try {
            connection = DBUtil.getThreadCon();
            DBUtil.startTransaction();

            userDao.insert(user);
            User userInfo = userDao.queryByPhone(user.getPhone());
            login.setUserId(userInfo.getId());
            loginDao.insert(login);
            //提交事务
            DBUtil.commitTransaction();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                DBUtil.rollBackTransaction();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            if (connection != null) {
                //将连接返回给连接池,同时从ThreadLocal中移除
                DBUtil.closeConnection();
            }
        }
        return true;
    }

    @Override
    public Boolean update(Login login) {
        try {
            loginDao.update(login);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeConnection();
        }
        return true;
    }

    @Override
    public Login queryById(Integer id) {
        Login login = null;
        try {
            login = loginDao.queryById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeConnection();
        }
        return login;
    }

    @Override
    public Login checkAccount(String account) {
        Login login = null;
        try {
            login = loginDao.queryByAccount(account);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeConnection();
        }
        return login;
    }

    @Override
    public Login checkAccountAndPassword(String account, String password) {
        Login login = null;
        try {
            login = loginDao.queryByAccountAndPassword(account, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeConnection();
        }
        return login;
    }
}
