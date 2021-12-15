package com.sc.dao;

import com.sc.model.TradeFlow;
import com.sc.vo.Page;
import com.sc.vo.PurchaseVo;
import com.sc.vo.TradeFlowVo;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface TradeFlowDao {

    int insert(TradeFlow tradeFlow) throws SQLException;

    List<TradeFlow> queryByMonth() throws SQLException;

    List<TradeFlowVo> queryByConditionAndPage(TradeFlowVo tradeFlowVo, Page page) throws SQLException;

    int totalByCondition(TradeFlowVo tradeFlowVo) throws SQLException;

}
