package com.sc.dao;

import com.sc.model.FactoryBook;
import com.sc.vo.Page;

import java.sql.SQLException;
import java.util.List;

public interface FactoryBookDao {

    List<FactoryBook> queryByConditionAndPage(FactoryBook factoryBook, Page page) throws SQLException;

    int totalByCondition(FactoryBook factoryBook) throws SQLException;
}
