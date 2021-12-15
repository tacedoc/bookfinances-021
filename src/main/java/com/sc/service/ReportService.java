package com.sc.service;

import com.sc.model.Report;
import com.sc.vo.Page;

import java.util.List;

public interface ReportService {
    /**
     * 添加一条月度报表
     * @param report
     * @return
     */
    int insert(Report report);

    /**
     * 计算上月某个交易类型的总交易金额
     * @param payee
     * @param year
     * @param month
     * @return
     */
    double sumByPayee(String payee,String year,String month);

    /**
     * 根据条件查询报表信息
     * @param year
     * @param month
     * @param page
     * @return
     */
    List<Report> showReportList(String year,String month, Page page);

    /**
     * 根据条件设置页码等信息
     * @param year
     * @param month
     * @return
     */
    Page createPageInfo(String year,String month);
}
