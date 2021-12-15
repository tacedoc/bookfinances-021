package com.sc.dao;

import com.sc.model.Borrow;
import com.sc.vo.BorrowVo;
import com.sc.vo.Page;

import java.sql.SQLException;
import java.util.List;

public interface BorrowDao {

    /**
     * 添加一条借阅记录
     * @param borrow
     * @return
     * @throws SQLException
     */
    int insert(Borrow borrow) throws SQLException;

    int update(Borrow borrow) throws SQLException;

    Borrow queryById(Integer id) throws SQLException;

    /**
     * 根据账号ID查询，实际归还日期为空(即：未归还的借阅记录)
     * @param loginId
     * @return
     * @throws SQLException
     */
    List<Borrow> queryByLoginId(Integer loginId) throws SQLException;

    List<BorrowVo> queryByConditionAndPage(BorrowVo borrowVo, Page page) throws SQLException;

    int totalByCondition(BorrowVo borrowVo) throws SQLException;
}
