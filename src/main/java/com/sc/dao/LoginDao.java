package com.sc.dao;

import com.sc.model.Login;

import java.sql.SQLException;

public interface LoginDao {

    int insert(Login login) throws SQLException;

    int update(Login login) throws SQLException;

    Login queryById(Integer id) throws SQLException;

    Login queryByAccount(String account) throws SQLException;

    Login queryByAccountAndPassword(String account, String password) throws SQLException;
}
