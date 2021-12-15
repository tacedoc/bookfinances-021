package com.sc.dao.impl;

import com.sc.dao.UserDao;
import com.sc.model.Book;
import com.sc.model.User;
import com.sc.util.DBUtil;
import com.sc.vo.Page;
import com.sc.vo.UserVo;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public int insert(User user) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "insert into user(username, phone, balance, role_id) values(?,?,?,?)";
        Object[] params = {user.getUsername(),user.getPhone(),user.getBalance(),user.getRoleId()};
        int count = 0;
        count = queryRunner.update(DBUtil.getThreadCon(),sql,params);
        return count;
    }

    @Override
    public int update(User user) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<>();
        sql.append("update user set id=?");
        paramList.add(user.getId());
        if (user.getUsername() != null && !"".equals(user.getUsername())) {
            sql.append(",username=?");
            paramList.add(user.getUsername());
        }
        if (user.getPhone() != null && !"".equals(user.getPhone())) {
            sql.append(",phone=?");
            paramList.add(user.getPhone());
        }
        if (user.getBalance() != null) {
            sql.append(",balance=?");
            paramList.add(user.getBalance());
        }
        if (user.getRoleId() != null) {
            sql.append(",role_id=?");
            paramList.add(user.getRoleId());
        }
        sql.append(" where id=?");
        paramList.add(user.getId());
        Object[] params = paramList.toArray();
        int count = 0;
        count = queryRunner.update(DBUtil.getThreadCon(), sql.toString(), params);
        return count;
    }

    @Override
    public User queryById(Integer id) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select id, username, phone, balance, role_id as roleId from user where id=?";
        Object[] params = {id};
        User user = null;
        user = queryRunner.query(DBUtil.getThreadCon(),sql, new BeanHandler<>(User.class),params);
        return user;
    }

    @Override
    public User queryByPhone(String phone) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select id, username, phone, balance, role_id as roleId from user where phone=?";
        Object[] params = {phone};
        User user = null;
        user = queryRunner.query(DBUtil.getThreadCon(),sql, new BeanHandler<>(User.class),params);
        return user;
    }

    @Override
    public List<UserVo> queryByConditionAndPage(UserVo userVo, Page page) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<>();
        sql.append("select a.id,a.account,a.available,b.username,b.phone,b.balance,b.role_id as roleId,c.name as roleName " +
                "from login a " +
                "left join user b " +
                "on a.user_id=b.id " +
                "left join role c " +
                "on b.role_id=c.id " +
                "where 1=1");
        if (userVo.getAccount() != null && !"".equals(userVo.getAccount())) {
            sql.append(" and a.account=?");
            paramList.add(userVo.getAccount());
        }
        if (userVo.getPhone() != null && !"".equals(userVo.getPhone())) {
            sql.append(" and b.phone=?");
            paramList.add(userVo.getPhone());
        }
        if (userVo.getAvailable() != null) {
            sql.append(" and a.available=?");
            paramList.add(userVo.getAvailable());
        }
        sql.append(" limit ?,?");
        paramList.add((page.getCurrentPage() - 1) * Page.PAGESIZE);
        paramList.add(Page.PAGESIZE);
        Object[] params = paramList.toArray();
        List<UserVo> list;
        list = queryRunner.query(DBUtil.getThreadCon(), sql.toString(), new BeanListHandler<>(UserVo.class), params);
        return list;
    }

    @Override
    public int totalByCondition(UserVo userVo) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<>();
        sql.append("select count(1)" +
                "from login a " +
                "left join user b " +
                "on a.user_id=b.id " +
                "where 1=1");
        if (userVo.getAccount() != null && !"".equals(userVo.getAccount())) {
            sql.append(" and a.account=?");
            paramList.add(userVo.getAccount());
        }
        if (userVo.getPhone() != null && !"".equals(userVo.getPhone())) {
            sql.append(" and b.phone=?");
            paramList.add(userVo.getPhone());
        }
        if (userVo.getAvailable() != null) {
            sql.append(" and a.available=?");
            paramList.add(userVo.getAvailable());
        }
        Object[] params = paramList.toArray();
        Long count = null;
        count = queryRunner.query(DBUtil.getThreadCon(), sql.toString(), new ScalarHandler<>(), params);
        if (count != null) {
            return count.intValue();
        }
        return 0;
    }


}
