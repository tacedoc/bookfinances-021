package com.sc.service;

import com.sc.model.Book;
import com.sc.vo.Page;

import java.util.List;

public interface BookService {

    /**
     * 添加一条图书记录,返回添加记录的ID
     * @param book
     * @return
     */
    Integer insert(Book book);

    /**
     * 更新图书信息
     * @param book
     * @return
     */
    Boolean update(Book book);

    /**
     * 根据ID查询图书信息
     * @param id
     * @return
     */
    Book queryById(Integer id);

    /**
     * 根据条件查询图书信息
     * @param book
     * @param page
     * @return
     */
    List<Book> showBookList(Book book, Page page);

    /**
     * 根据条件设置页码等信息
     * @param book
     * @return
     */
    Page createPageInfo(Book book);
}
