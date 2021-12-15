package com.sc.service.impl;

import com.sc.dao.BookDao;
import com.sc.dao.PurchaseDao;
import com.sc.dao.TradeFlowDao;
import com.sc.dao.impl.*;
import com.sc.model.*;
import com.sc.service.BookService;
import com.sc.service.PurchaseService;
import com.sc.service.TradeFlowService;
import com.sc.util.DBUtil;
import com.sc.vo.Page;
import com.sc.vo.PurchaseVo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class PurchaseServiceImpl implements PurchaseService {

    private PurchaseDao purchaseDao = new PurchaseDaoImpl();
    private BookService bookService = new BookServiceImpl();
    private TradeFlowService tradeFlowService = new TradeFlowServiceImpl();

    @Override
    public Boolean addPurchaseRecord(Book book, Purchase purchase) {

        Connection connection = null;
        try {
            connection = DBUtil.getThreadCon();
            DBUtil.startTransaction();
            //判断图书是否已经存在,存在则只更新库存，反之添加
            List<Book> bookList = bookService.showBookList(book, new Page());
            if (bookList.size() > 0){
                Book existBook = bookList.get(0);
                existBook.setRemainCount(existBook.getRemainCount()+purchase.getCount());
                existBook.setTotalCount(existBook.getTotalCount()+purchase.getCount());
                bookService.update(existBook);
                book.setId(existBook.getId());
            }else {
                book.setRemainCount(purchase.getCount());
                book.setTotalCount(purchase.getCount());
                Integer id = bookService.insert(book);
                book.setId(id);
            }
            //设置交易的图书ID,然后添加交易记录
            purchase.setBookId(book.getId());
            purchaseDao.insert(purchase);

            //添加交易的流水记录
            TradeFlow tradeFlow = new TradeFlow();
            tradeFlow.setPayer(purchase.getLoginId());
            tradeFlow.setPayee(book.getVendor());
            tradeFlow.setTradeType("图书外购");
            tradeFlow.setTradeAmount(purchase.getUnitPrice()*purchase.getCount());
            tradeFlow.setTradeDate(new Date());
            tradeFlowService.insert(tradeFlow);
            //提交事务
            DBUtil.commitTransaction();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                DBUtil.rollBackTransaction();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            if (connection != null) {
                //将连接返回给连接池,同时从ThreadLocal中移除
                DBUtil.closeConnection();
            }
        }
        return true;
    }

    @Override
    public List<PurchaseVo> showPurchaseVoList(PurchaseVo purchaseVo, Page page) {
        List<PurchaseVo> list = null;
        try {
            list = purchaseDao.queryByConditionAndPage(purchaseVo, page);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeConnection();
        }
        return list;
    }

    @Override
    public Page createPageInfo(PurchaseVo purchaseVo) {
        Page page = new Page();
        try {
            page.setTotalCount(purchaseDao.totalByCondition(purchaseVo));
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
