package com.sc.service;

import com.sc.model.FactoryBook;
import com.sc.vo.Page;

import java.util.List;

public interface FactoryBookService {

    /**
     * 根据条件查询工厂图书信息
     * @param factoryBook
     * @param page
     * @return
     */
    List<FactoryBook> showFactoryBookList(FactoryBook factoryBook, Page page);

    /**
     * 根据条件设置页码等信息
     * @param factoryBook
     * @return
     */
    Page createPageInfo(FactoryBook factoryBook);
}
