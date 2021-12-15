package com.sc.service;

import com.sc.model.TradeFlow;
import com.sc.vo.Page;
import com.sc.vo.PurchaseVo;
import com.sc.vo.TradeFlowVo;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface TradeFlowService {
    /**
     * 添加一条交易流水记录
     * @param tradeFlow
     * @return
     */
    Boolean insert(TradeFlow tradeFlow);

    /**
     * 查询上月交易流水记录
     * @return
     */
    List<TradeFlow> queryByMonth();

    /**
     * 根据条件查询流水信息
     * @param tradeFlowVo
     * @param page
     * @return
     */
    List<TradeFlowVo> showTradeFlowVoList(TradeFlowVo tradeFlowVo, Page page);

    /**
     * 根据条件设置页码等信息
     * @param tradeFlowVo
     * @return
     */
    Page createPageInfo(TradeFlowVo tradeFlowVo);
}
