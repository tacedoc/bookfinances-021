package com.sc.service.impl;

import com.sc.dao.ReportDao;
import com.sc.dao.impl.ReportDaoImpl;
import com.sc.model.Book;
import com.sc.model.Report;
import com.sc.service.ReportService;
import com.sc.util.DBUtil;
import com.sc.vo.Page;

import java.sql.SQLException;
import java.util.List;

public class ReportServiceImpl implements ReportService {

    private ReportDao reportDao = new ReportDaoImpl();

    @Override
    public int insert(Report report) {
        int count = 0;
        try {
            count = reportDao.insert(report);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeConnection();
        }
        return count;
    }

    @Override
    public double sumByPayee(String payee,String year,String month) {
        double totalAmount = 0.0;
        try {
            Double sum = reportDao.sumByPayee(payee, year, month);
            if (sum != null){
                totalAmount = sum;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeConnection();
        }
        return totalAmount;
    }

    @Override
    public List<Report> showReportList(String year, String month, Page page) {
        List<Report> list = null;
        try {
            list = reportDao.queryByConditionAndPage(year, month, page);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeConnection();
        }
        return list;
    }

    @Override
    public Page createPageInfo(String year, String month) {
        Page page = new Page();
        try {
            page.setTotalCount(reportDao.totalByCondition(year,month));
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
