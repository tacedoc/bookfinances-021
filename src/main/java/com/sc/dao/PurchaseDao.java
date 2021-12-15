package com.sc.dao;

import com.sc.model.Book;
import com.sc.model.Purchase;
import com.sc.vo.Page;
import com.sc.vo.PurchaseVo;

import java.sql.SQLException;
import java.util.List;

public interface PurchaseDao {

    int insert(Purchase purchase) throws SQLException;

    List<PurchaseVo> queryByConditionAndPage(PurchaseVo purchaseVo, Page page) throws SQLException;

    int totalByCondition(PurchaseVo purchaseVo) throws SQLException;
}
