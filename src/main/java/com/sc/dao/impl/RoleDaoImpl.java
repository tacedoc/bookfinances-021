package com.sc.dao.impl;

import com.sc.dao.RoleDao;
import com.sc.model.Role;
import com.sc.util.DBUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class RoleDaoImpl implements RoleDao {

    @Override
    public Role queryById(Integer id) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select id, name from role where id=?";
        Object[] params = {id};
        Role role = null;
        role = queryRunner.query(DBUtil.getThreadCon(), sql, new BeanHandler<>(Role.class), params);
        return role;
    }
}
