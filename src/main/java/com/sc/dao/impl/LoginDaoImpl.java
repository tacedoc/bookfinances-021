package com.sc.dao.impl;

import com.sc.dao.LoginDao;
import com.sc.model.Login;
import com.sc.util.DBUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginDaoImpl implements LoginDao {

    @Override
    public int insert(Login login) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "insert into login(account, password, user_id, available) values(?,?,?,?)";
        Object[] params = {login.getAccount(),login.getPassword(),login.getUserId(),login.getAvailable()};
        int count = 0;
        count = queryRunner.update(DBUtil.getThreadCon(),sql,params);
        return count;
    }

    @Override
    public int update(Login login) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<>();
        sql.append("update login set id=?");
        paramList.add(login.getId());
        if (login.getPassword() != null && !"".equals(login.getPassword())) {
            sql.append(",password=?");
            paramList.add(login.getPassword());
        }
        if (login.getAvailable() != null) {
            sql.append(",available=?");
            paramList.add(login.getAvailable());
        }
        sql.append(" where id=?");
        paramList.add(login.getId());
        Object[] params = paramList.toArray();
        int count = 0;
        count = queryRunner.update(DBUtil.getThreadCon(), sql.toString(), params);
        return count;
    }

    @Override
    public Login queryById(Integer id) throws SQLException {
        //使用QueryRunner时，必须要使得查询出来的表列名与属性名一致，否则赋值可能为空
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select id,account,password,user_id as userId,available from login where id=?";
        Object[] params = {id};
        Login login = null;
        login = queryRunner.query(DBUtil.getThreadCon(), sql, new BeanHandler<>(Login.class), params);
        return login;
    }

    @Override
    public Login queryByAccount(String account) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select id,account,password,user_id as userId,available from login where account=?";
        Object[] params = {account};
        Login login = null;
        login = queryRunner.query(DBUtil.getThreadCon(), sql, new BeanHandler<>(Login.class), params);
        return login;
    }

    @Override
    public Login queryByAccountAndPassword(String account, String password) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select id, account, password, user_id as userId, available from login where account=? and password=?";
        Object[] params = {account, password};
        Login login = null;
        login = queryRunner.query(DBUtil.getThreadCon(), sql, new BeanHandler<>(Login.class), params);
        return login;
    }
}
