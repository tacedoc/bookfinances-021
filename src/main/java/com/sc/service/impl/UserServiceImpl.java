package com.sc.service.impl;

import com.sc.dao.LoginDao;
import com.sc.dao.TradeFlowDao;
import com.sc.dao.UserDao;
import com.sc.dao.impl.LoginDaoImpl;
import com.sc.dao.impl.TradeFlowDaoImpl;
import com.sc.dao.impl.UserDaoImpl;
import com.sc.model.Book;
import com.sc.model.Login;
import com.sc.model.TradeFlow;
import com.sc.model.User;
import com.sc.service.LoginService;
import com.sc.service.UserService;
import com.sc.util.DBUtil;
import com.sc.vo.Page;
import com.sc.vo.UserVo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class UserServiceImpl implements UserService {

    private LoginDao loginDao = new LoginDaoImpl();

    private UserDao userDao = new UserDaoImpl();

    private TradeFlowDao tradeFlowDao = new TradeFlowDaoImpl();

    @Override
    public Boolean update(User user) {
        try {
            userDao.update(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeConnection();
        }
        return true;
    }

    @Override
    public Boolean rechargeBalance(Integer loginId, Double balance) {
        Connection connection = null;
        try {
            connection = DBUtil.getThreadCon();
            DBUtil.startTransaction();

            Login login = loginDao.queryById(loginId);
            User user = userDao.queryById(login.getUserId());
            user.setBalance(user.getBalance() + balance);
            userDao.update(user);

            TradeFlow tradeFlow = new TradeFlow();
            tradeFlow.setPayer(loginId);
            tradeFlow.setPayee("图书馆");
            tradeFlow.setTradeType("用户充值");
            tradeFlow.setTradeAmount(balance);
            tradeFlow.setTradeDate(new Date());

            tradeFlowDao.insert(tradeFlow);
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
    public User queryById(Integer id) {
        User user = null;
        try {
            user = userDao.queryById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeConnection();
        }
        return user;
    }

    @Override
    public User checkPhone(String phone){
        User user = null;
        try {
            user = userDao.queryByPhone(phone);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeConnection();
        }
        return user;
    }

    @Override
    public List<UserVo> showUserList(UserVo userVo, Page page) {
        List<UserVo> list = null;
        try {
            list = userDao.queryByConditionAndPage(userVo, page);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeConnection();
        }
        return list;
    }

    @Override
    public Page createPageInfo(UserVo userVo) {
        Page page = new Page();
        try {
            page.setTotalCount(userDao.totalByCondition(userVo));
            if (page.getTotalCount() % Page.PAGESIZE == 0) {
                page.setTotalPageNum(page.getTotalCount() / Page.PAGESIZE);
            } else {
                page.setTotalPageNum(page.getTotalCount() / Page.PAGESIZE + 1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeConnection();
        }
        return page;
    }
}
