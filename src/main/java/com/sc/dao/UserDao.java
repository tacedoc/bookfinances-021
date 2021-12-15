package com.sc.dao;

import com.sc.model.Book;
import com.sc.model.User;
import com.sc.vo.Page;
import com.sc.vo.UserVo;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    /**
     * 添加一条用户信息
     * @param user
     * @return
     * @throws SQLException
     */
    int insert(User user) throws SQLException;

    /**
     * 修改一条用户信息
     * @param user
     * @return
     * @throws SQLException
     */
    int update(User user) throws SQLException;

    /**
     * 根据用户ID查询用户信息
     * @param id
     * @return
     * @throws SQLException
     */
    User queryById(Integer id) throws SQLException;

    /**
     * 根据手机号，查询用户是否存在
     * @param phone 手机号
     * @return
     * @throws SQLException
     */
    User queryByPhone(String phone) throws SQLException;

    /**
     * 根据条件查找用户信息
     * @param userVo
     * @return
     */
    List<UserVo> queryByConditionAndPage(UserVo userVo, Page page) throws SQLException;

    int totalByCondition(UserVo userVo) throws SQLException;

}
