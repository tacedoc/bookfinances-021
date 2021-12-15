package com.sc.dao.impl;

import com.sc.dao.TradeFlowDao;
import com.sc.model.TradeFlow;
import com.sc.util.DBUtil;
import com.sc.vo.Page;
import com.sc.vo.PurchaseVo;
import com.sc.vo.TradeFlowVo;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TradeFlowDaoImpl implements TradeFlowDao {

    @Override
    public int insert(TradeFlow tradeFlow) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "insert into trade_flow(payer,payee,trade_type,trade_amount,trade_date) values(?,?,?,?,?)";
        Object[] params = {tradeFlow.getPayer(),tradeFlow.getPayee(),tradeFlow.getTradeType(),tradeFlow.getTradeAmount(),tradeFlow.getTradeDate()};
        int count = 0;
        count = queryRunner.update(DBUtil.getThreadCon(),sql,params);
        return count;
    }

    @Override
    public List<TradeFlow> queryByMonth() throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        //查询上月交易流失
        String sql = "select id,payer,payee,trade_type as tradeType,trade_amount as tradeAmount,trade_date as tradeDate from trade_flow where period_diff( date_format(now(),'%Y%m'), date_format(trade_date,'%Y%m') ) =1";
        List<TradeFlow> list;
        list = queryRunner.query(DBUtil.getThreadCon(), sql, new BeanListHandler<>(TradeFlow.class));
        return list;
    }

    @Override
    public List<TradeFlowVo> queryByConditionAndPage(TradeFlowVo tradeFlowVo, Page page) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<>();
        sql.append("select a.id,d.username as payer,a.payee,a.trade_type as tradeType, a.trade_amount as tradeAmount,a.trade_date as tradeDate");
        sql.append(" from trade_flow a");
        sql.append(" left join (select b.id,c.username from login b inner join user c on b.user_id=c.id) d");
        sql.append(" on a.payer=d.id where 1=1");
        if (tradeFlowVo.getPayer() != null && !"".equals(tradeFlowVo.getPayer())){
            sql.append(" and d.username like '%' ? '%'");
            paramList.add(tradeFlowVo.getPayer());
        }
        if (tradeFlowVo.getTradeType() != null && !"".equals(tradeFlowVo.getTradeType())){
            sql.append(" and a.trade_type = ?");
            paramList.add(tradeFlowVo.getTradeType());
        }
        if (tradeFlowVo.getStartDate() != null){
            sql.append(" and a.trade_date >= ?");
            paramList.add(tradeFlowVo.getStartDate());
        }
        if (tradeFlowVo.getEndDate() != null){
            sql.append(" and a.trade_date <= ?");
            paramList.add(tradeFlowVo.getEndDate());
        }
        sql.append(" limit ?,?");
        paramList.add((page.getCurrentPage() - 1) * Page.PAGESIZE);
        paramList.add(Page.PAGESIZE);
        Object[] params = paramList.toArray();
        List<TradeFlowVo> list;
        list = queryRunner.query(DBUtil.getThreadCon(), sql.toString(), new BeanListHandler<>(TradeFlowVo.class), params);
        return list;
    }

    @Override
    public int totalByCondition(TradeFlowVo tradeFlowVo) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<>();
        sql.append("select count(1)");
        sql.append(" from trade_flow a");
        sql.append(" left join (select b.id,c.username from login b inner join user c on b.user_id=c.id) d");
        sql.append(" on a.payer=d.id where 1=1");
        if (tradeFlowVo.getPayer() != null && !"".equals(tradeFlowVo.getPayer())){
            sql.append(" and d.username like '%' ? '%'");
            paramList.add(tradeFlowVo.getPayer());
        }
        if (tradeFlowVo.getTradeType() != null && !"".equals(tradeFlowVo.getTradeType())){
            sql.append(" and a.trade_type = ?");
            paramList.add(tradeFlowVo.getTradeType());
        }
        if (tradeFlowVo.getStartDate() != null){
            sql.append(" and a.trade_date >= ?");
            paramList.add(tradeFlowVo.getStartDate());
        }
        if (tradeFlowVo.getEndDate() != null){
            sql.append(" and a.trade_date <= ?");
            paramList.add(tradeFlowVo.getEndDate());
        }
        Object[] params = paramList.toArray();
        Long count = null;
        count = queryRunner.query(DBUtil.getThreadCon(), sql.toString(), new ScalarHandler<>(), params);
        if (count != null) {
            return count.intValue();
        }
        return 0;
    }
}
