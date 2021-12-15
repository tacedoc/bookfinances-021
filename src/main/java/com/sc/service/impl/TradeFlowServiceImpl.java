package com.sc.service.impl;

import com.sc.dao.TradeFlowDao;
import com.sc.dao.impl.TradeFlowDaoImpl;
import com.sc.model.TradeFlow;
import com.sc.service.TradeFlowService;
import com.sc.util.DBUtil;
import com.sc.vo.Page;
import com.sc.vo.PurchaseVo;
import com.sc.vo.TradeFlowVo;

import java.sql.SQLException;
import java.util.List;

public class TradeFlowServiceImpl implements TradeFlowService {

    private TradeFlowDao tradeFlowDao = new TradeFlowDaoImpl();

    @Override
    public Boolean insert(TradeFlow tradeFlow) {
        try {
            tradeFlowDao.insert(tradeFlow);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeConnection();
        }
        return true;
    }

    @Override
    public List<TradeFlow> queryByMonth() {
        List<TradeFlow> list = null;
        try {
            list = tradeFlowDao.queryByMonth();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeConnection();
        }
        return list;
    }

    @Override
    public List<TradeFlowVo> showTradeFlowVoList(TradeFlowVo tradeFlowVo, Page page) {
        List<TradeFlowVo> list = null;
        try {
            list = tradeFlowDao.queryByConditionAndPage(tradeFlowVo, page);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeConnection();
        }
        return list;
    }

    @Override
    public Page createPageInfo(TradeFlowVo tradeFlowVo) {
        Page page = new Page();
        try {
            page.setTotalCount(tradeFlowDao.totalByCondition(tradeFlowVo));
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
