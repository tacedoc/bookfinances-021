package com.sc.dao;

import com.sc.model.Role;

import java.sql.SQLException;

public interface RoleDao {

    Role queryById(Integer id) throws SQLException;
}
