package com.sc.service.impl;

import com.sc.dao.FactoryBookDao;
import com.sc.dao.impl.FactoryBookDaoImpl;
import com.sc.model.FactoryBook;
import com.sc.service.FactoryBookService;
import com.sc.util.DBUtil;
import com.sc.vo.Page;

import java.sql.SQLException;
import java.util.List;

public class FactoryBookServiceImpl implements FactoryBookService {

    private FactoryBookDao factoryBookDao = new FactoryBookDaoImpl();

    @Override
    public List<FactoryBook> showFactoryBookList(FactoryBook factoryBook, Page page) {
        List<FactoryBook> list = null;
        try {
            list = factoryBookDao.queryByConditionAndPage(factoryBook, page);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeConnection();
        }
        return list;
    }

    @Override
    public Page createPageInfo(FactoryBook factoryBook) {
        Page page = new Page();
        try {
            page.setTotalCount(factoryBookDao.totalByCondition(factoryBook));
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
