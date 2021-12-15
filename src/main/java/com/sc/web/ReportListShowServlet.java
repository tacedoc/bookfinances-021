package com.sc.web;

import com.sc.model.Report;
import com.sc.service.ReportService;
import com.sc.service.TradeFlowService;
import com.sc.service.impl.ReportServiceImpl;
import com.sc.service.impl.TradeFlowServiceImpl;
import com.sc.util.DateFormatUtil;
import com.sc.vo.Page;
import com.sc.vo.ReportVo;
import com.sc.vo.TradeFlowVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "reportListShowServlet", urlPatterns = {"/report/showReportList"})
public class ReportListShowServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String year = request.getParameter("year");
        String month = request.getParameter("month");
        String currentPage = request.getParameter("currentPage");
        Page page = new Page();
        if (currentPage != null && !"".equals(currentPage)){
            page.setCurrentPage(Integer.parseInt(currentPage));
        }

        ReportService reportService = new ReportServiceImpl();
        List<Report> reportList = reportService.showReportList(year, month, page);
        List<ReportVo> reportVoList = new ArrayList<>();
        for (Report report : reportList) {
            ReportVo reportVo = new ReportVo();
            reportVo.setId(report.getId());
            reportVo.setMonthGet(report.getMonthGet());
            reportVo.setMonthOut(report.getMonthOut());
            reportVo.setReportDate(report.getReportDate());
            String profitAndLoss;
            if (report.getMonthGet() >= report.getMonthOut()){
                profitAndLoss = "盈利" + (report.getMonthGet() - report.getMonthOut());
            }else {
                profitAndLoss = "亏损" + (report.getMonthOut() - report.getMonthGet());
            }
            reportVo.setProfitAndLoss(profitAndLoss);

            reportVoList.add(reportVo);
        }
        Page pageInfo = reportService.createPageInfo(year,month);
        if (currentPage != null && !"".equals(currentPage)) {
            pageInfo.setCurrentPage(Integer.parseInt(currentPage));
        }

        request.setAttribute("reportVoList",reportVoList);
        request.setAttribute("year",year);
        request.setAttribute("month",month);
        request.setAttribute("pageInfo",pageInfo);

        request.getRequestDispatcher("/WEB-INF/view/report/reportList.jsp").forward(request,response);

    }
}
