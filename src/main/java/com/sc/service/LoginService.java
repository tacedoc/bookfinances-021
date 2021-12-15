package com.sc.service;


import com.sc.model.Login;
import com.sc.model.User;

public interface LoginService {

    /**
     * 用户账号信息注册
     * @param login
     * @param user
     * @return
     */
    Boolean registry(Login login, User user);

    /**
     * 修改账户信息
     * @param login
     * @return
     */
    Boolean update(Login login);

    /**
     * 根据账号ID查询信息
     * @param id
     * @return
     */
    Login queryById(Integer id);

    /**
     * 查询账号是否存在
     * @param account
     * @return
     */
    Login checkAccount(String account);

    /**
     * 验证登录账号及密码
     * @param account
     * @param password
     * @return
     */
    Login checkAccountAndPassword(String account, String password);

}
