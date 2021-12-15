package com.sc.service.impl;

import com.sc.dao.BookDao;
import com.sc.dao.impl.BookDaoImpl;
import com.sc.model.Book;
import com.sc.service.BookService;
import com.sc.util.DBUtil;
import com.sc.vo.Page;

import java.sql.SQLException;
import java.util.List;

public class BookServiceImpl implements BookService {

    private BookDao bookDao = new BookDaoImpl();

    @Override
    public Integer insert(Book book) {
        Integer id = null;
        try {
            bookDao.insert(book);
            id = bookDao.getInsertId();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeConnection();
        }
        return id;
    }

    @Override
    public Boolean update(Book book) {
        try {
            bookDao.update(book);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeConnection();
        }
        return true;
    }

    @Override
    public Book queryById(Integer id) {
        Book book = null;
        try {
            book = bookDao.queryById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeConnection();
        }
        return book;
    }

    @Override
    public List<Book> showBookList(Book book, Page page) {
        List<Book> list = null;
        try {
            list = bookDao.queryByConditionAndPage(book, page);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeConnection();
        }
        return list;
    }

    @Override
    public Page createPageInfo(Book book) {
        Page page = new Page();
        try {
            page.setTotalCount(bookDao.totalByCondition(book));
            if (page.getTotalCount() % Page.PAGESIZE == 0) {
                page.setTotalPageNum(page.getTotalCount() / Page.PAGESIZE);
            } else {
                page.setTotalPageNum(page.getTotalCount() / Page.PAGESIZE + 1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeConnection();
        }
        return page;
    }
}
