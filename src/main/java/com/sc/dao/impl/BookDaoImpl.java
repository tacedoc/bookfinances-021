package com.sc.dao.impl;


import com.sc.dao.BookDao;
import com.sc.model.Book;
import com.sc.util.DBUtil;
import com.sc.vo.Page;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {

    @Override
    public int insert(Book book) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "insert into book(name,author,vendor,remain_count,total_count,unit_price,borrow_price,del_flag) value(?,?,?,?,?,?,?,?)";
        Object[] params = {book.getName(), book.getAuthor(), book.getVendor(), book.getRemainCount(),
                book.getTotalCount(), book.getUnitPrice(), book.getBorrowPrice(), book.getDelFlag()};
        int count = 0;
        count = queryRunner.update(DBUtil.getThreadCon(), sql, params);
        return count;
    }

    @Override
    public int update(Book book) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<>();
        sql.append("update book set id=?");
        paramList.add(book.getId());
        if (book.getRemainCount() != null) {
            sql.append(",remain_count=?");
            paramList.add(book.getRemainCount());
        }
        if (book.getTotalCount() != null) {
            sql.append(",total_count=?");
            paramList.add(book.getTotalCount());
        }
        if (book.getUnitPrice() != null) {
            sql.append(",unit_price=?");
            paramList.add(book.getUnitPrice());
        }
        if (book.getBorrowPrice() != null) {
            sql.append(",borrow_price=?");
            paramList.add(book.getBorrowPrice());
        }
        if (book.getDelFlag() != null) {
            sql.append(",del_flag=?");
            paramList.add(book.getDelFlag());
        }
        sql.append(" where id=?");
        paramList.add(book.getId());
        Object[] params = paramList.toArray();
        int count = 0;
        count = queryRunner.update(DBUtil.getThreadCon(), sql.toString(), params);
        return count;
    }

    @Override
    public Book queryById(Integer id) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select id,name,author,vendor,remain_count as remainCount,total_count as totalCount,unit_price as unitPrice,borrow_price as borrowPrice,del_flag as delFlag from book where id=?";
        Object[] params = {id};
        Book book = null;
        book = queryRunner.query(DBUtil.getThreadCon(), sql, new BeanHandler<>(Book.class), params);
        return book;
    }

    @Override
    public List<Book> queryByConditionAndPage(Book book, Page page) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<>();
        sql.append("select id,name,author,vendor,remain_count as remainCount,total_count as totalCount,unit_price as unitPrice,borrow_price as borrowPrice,del_flag as delFlag from book where 1=1");
        if (book.getName() != null && !"".equals(book.getName())) {
            sql.append(" and name like '%' ? '%'");
            paramList.add(book.getName());
        }
        if (book.getAuthor() != null && !"".equals(book.getAuthor())) {
            sql.append(" and author like '%' ? '%'");
            paramList.add(book.getAuthor());
        }
        if (book.getVendor() != null && !"".equals(book.getVendor())) {
            sql.append(" and vendor like '%' ? '%'");
            paramList.add(book.getVendor());
        }
        if (book.getDelFlag() != null) {
            sql.append(" and del_flag=?");
            paramList.add(book.getDelFlag());
        }
        sql.append(" limit ?,?");
        paramList.add((page.getCurrentPage() - 1) * Page.PAGESIZE);
        paramList.add(Page.PAGESIZE);
        Object[] params = paramList.toArray();
        List<Book> list;
        list = queryRunner.query(DBUtil.getThreadCon(), sql.toString(), new BeanListHandler<>(Book.class), params);
        return list;
    }

    @Override
    public int totalByCondition(Book book) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<>();
        sql.append("select count(1) from book where 1=1");
        if (book.getName() != null && !"".equals(book.getName())) {
            sql.append(" and name like '%' ? '%'");
            paramList.add(book.getName());
        }
        if (book.getAuthor() != null && !"".equals(book.getAuthor())) {
            sql.append(" and author like '%' ? '%'");
            paramList.add(book.getAuthor());
        }
        if (book.getVendor() != null && !"".equals(book.getVendor())) {
            sql.append(" and vendor like '%' ? '%'");
            paramList.add(book.getVendor());
        }
        if (book.getDelFlag() != null) {
            sql.append(" and del_flag=?");
            paramList.add(book.getDelFlag());
        }
        Object[] params = paramList.toArray();
        Long count = null;
        count = queryRunner.query(DBUtil.getThreadCon(), sql.toString(), new ScalarHandler<>(), params);
        if (count != null) {
            return count.intValue();
        }
        return 0;
    }

    @Override
    public int getInsertId() throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select max(id) from book";
        Integer id = null;
        id = queryRunner.query(DBUtil.getThreadCon(), sql.toString(), new ScalarHandler<>());
        return id;
    }

}