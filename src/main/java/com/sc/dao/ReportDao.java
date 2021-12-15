package com.sc.dao;

import com.sc.model.Book;
import com.sc.model.Report;
import com.sc.vo.Page;

import java.sql.SQLException;
import java.util.List;

public interface ReportDao {

    int insert(Report report) throws SQLException;

    /**
     * 统计指定收款方、年月的总流水金额
     * @param payee
     * @param year
     * @param month
     * @return
     * @throws SQLException
     */
    Double sumByPayee(String payee,String year,String month) throws SQLException;

    List<Report> queryByConditionAndPage(String year, String month, Page page) throws SQLException;

    int totalByCondition(String year, String month) throws SQLException;
}
