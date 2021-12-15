package com.sc.service;

import com.sc.model.User;
import com.sc.vo.Page;
import com.sc.vo.UserVo;

import java.util.List;

public interface UserService {

    Boolean update(User user);

    /**
     * 充值金额
     * @param loginId
     * @param balance
     * @return
     */
    Boolean rechargeBalance(Integer loginId,Double balance);

    /**
     * 根据用户ID查询用户
     * @param id
     * @return
     */
    User queryById(Integer id);

    /**
     * 查询手机号是否存在
     * @param phone
     * @return
     */
    User checkPhone(String phone);

    /**
     * 根据条件查询用户信息
     * @param userVo
     * @param page
     * @return
     */
    List<UserVo> showUserList(UserVo userVo, Page page);

    /**
     * 根据条件设置页码等信息
     * @param userVo
     * @return
     */
    Page createPageInfo(UserVo userVo);
}
