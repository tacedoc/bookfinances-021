package com.sc.service;

import com.sc.model.Borrow;
import com.sc.vo.BorrowVo;
import com.sc.vo.Page;

import java.util.Date;
import java.util.List;

public interface BorrowService {

    /**
     * 添加一条借阅记录，同时减少图书剩余库存
     * @param borrow
     * @return
     */
    Boolean insert(Borrow borrow);

    /**
     * 修改借阅记录
     * @param borrow
     * @return
     */
    Boolean update(Borrow borrow);

    /**
     * 图书归还 （注：由于本程序设计时设定，借阅时只判定能否借阅，并不扣除费用，只在最后归还等操作下结算）
     * 1.先根据借阅记录查询是否逾期,并计算借阅费用
     * 2.修改用户余额
     * 3.修改图书库存
     * 4.修改借阅信息
     * 5.添加一条流水记录
     * @param borrowId
     * @return
     */
    Boolean bookReturn(Integer borrowId);

    /**
     * 图书借阅延期
     * @param borrowId
     * @param postponeDays 延期的天数
     * @return
     */
    Boolean bookPostpone(Integer borrowId, Integer postponeDays);

    /**
     * 图书报损，这里实现代码与图书归还有点类似
     *
     * @param borrowId
     * @return
     */
    Boolean bookDamage(Integer borrowId);
    /**
     * 根据ID查询借阅记录
     * @param id
     * @return
     */
    Borrow queryById(Integer id);

    /**
     * 借阅前的验证，验证用户是否有足够的可用余额，是否有尚未处理的逾期记录
     * @return true验证通过，反之不通过
     */
    Boolean borrowVerify(Borrow borrow);

    /**
     * 根据条件查询借阅信息
     * @param borrowVo
     * @param page
     * @return
     */
    List<BorrowVo> showBorrowList(BorrowVo borrowVo, Page page);

    /**
     * 根据条件设置页码等信息
     * @param borrowVo
     * @return
     */
    Page createPageInfo(BorrowVo borrowVo);

    /**
     * 逾期判断
     * @return 逾期返回true，否则返回false
     */
    boolean overdueVerify(Date beginDate, Integer promiseDay);
}
