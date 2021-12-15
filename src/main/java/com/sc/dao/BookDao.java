package com.sc.dao;

import com.sc.model.Book;
import com.sc.vo.Page;

import java.sql.SQLException;
import java.util.List;

public interface BookDao {

    int insert(Book book) throws SQLException;

    int update(Book book) throws SQLException;

    Book queryById(Integer id) throws SQLException;

    List<Book> queryByConditionAndPage(Book book, Page page) throws SQLException;

    int totalByCondition(Book book) throws SQLException;

    int getInsertId() throws SQLException;
}
