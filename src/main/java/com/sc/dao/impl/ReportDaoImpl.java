package com.sc.dao.impl;

import com.sc.dao.ReportDao;
import com.sc.model.Book;
import com.sc.model.Report;
import com.sc.model.TradeFlow;
import com.sc.util.DBUtil;
import com.sc.vo.Page;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportDaoImpl implements ReportDao {

    @Override
    public int insert(Report report) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "insert into report(report_date,month_out,month_get) value(?,?,?)";
        Object[] params = {report.getReportDate(),report.getMonthOut(),report.getMonthGet()};
        int count = 0;
        count = queryRunner.update(DBUtil.getThreadCon(), sql, params);
        return count;
    }

    @Override
    public Double sumByPayee(String payee, String year, String month) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        //查询上月交易流水
        String sql = "select sum(trade_amount) " +
                "from trade_flow " +
                "where year(trade_date)=? and month(trade_date)=?";
        Object[] params = {year, month};
        if (payee != null && !"".equals(payee)){
            sql = sql + " and payee=?";
            params = new Object[]{year, month, payee};
        }
        Double amount = null;
        amount = queryRunner.query(DBUtil.getThreadCon(), sql, new ScalarHandler<>(),params);
        return amount;
    }

    @Override
    public List<Report> queryByConditionAndPage(String year, String month, Page page) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<>();
        sql.append("select id,report_date as reportDate,month_out as monthOut, month_get as monthGet " +
                "from report " +
                "where 1=1");
        if (year != null && !"".equals(year)) {
            sql.append(" and year(report_date)=?");
            paramList.add(year);
        }
        if (month != null && !"".equals(month)) {
            sql.append(" and month(report_date)=?");
            paramList.add(month);
        }
        sql.append(" limit ?,?");
        paramList.add((page.getCurrentPage() - 1) * Page.PAGESIZE);
        paramList.add(Page.PAGESIZE);
        Object[] params = paramList.toArray();
        List<Report> list;
        list = queryRunner.query(DBUtil.getThreadCon(), sql.toString(), new BeanListHandler<>(Report.class), params);
        return list;

    }

    @Override
    public int totalByCondition(String year, String month) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<>();
        sql.append("select count(1) " +
                "from report " +
                "where 1=1");
        if (year != null && !"".equals(year)) {
            sql.append(" and year(report_date)=?");
            paramList.add(year);
        }
        if (month != null && !"".equals(month)) {
            sql.append(" and month(report_date)=?");
            paramList.add(month);
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
