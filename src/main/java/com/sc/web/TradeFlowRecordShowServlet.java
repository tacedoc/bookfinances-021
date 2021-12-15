package com.sc.web;

import com.sc.service.TradeFlowService;
import com.sc.service.impl.TradeFlowServiceImpl;
import com.sc.util.DateFormatUtil;
import com.sc.vo.Page;
import com.sc.vo.TradeFlowVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "tradeFlowRecordShowServlet", urlPatterns = {"/tradeFlow/showTradeFlowRecordList"})
public class TradeFlowRecordShowServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String payer = request.getParameter("payer");
        String tradeType = request.getParameter("tradeType");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String currentPage = request.getParameter("currentPage");
        Page page = new Page();
        if (currentPage != null && !"".equals(currentPage)){
            page.setCurrentPage(Integer.parseInt(currentPage));
        }

        TradeFlowVo tradeFlowVo = new TradeFlowVo();
        tradeFlowVo.setPayer(payer);
        tradeFlowVo.setTradeType(tradeType);
        tradeFlowVo.setStartDate(DateFormatUtil.formatToDate(startDate));
        tradeFlowVo.setEndDate(DateFormatUtil.formatToDate(endDate));

        TradeFlowService tradeFlowService = new TradeFlowServiceImpl();
        List<TradeFlowVo> tradeFlowVoList = tradeFlowService.showTradeFlowVoList(tradeFlowVo, page);
        Page pageInfo = tradeFlowService.createPageInfo(tradeFlowVo);
        if (currentPage != null && !"".equals(currentPage)) {
            pageInfo.setCurrentPage(Integer.parseInt(currentPage));
        }

        request.setAttribute("tradeFlowVoList",tradeFlowVoList);
        request.setAttribute("tradeFlowVoCondition",tradeFlowVo);
        request.setAttribute("startDate",startDate);
        request.setAttribute("endDate",endDate);
        request.setAttribute("pageInfo",pageInfo);

        request.getRequestDispatcher("/WEB-INF/view/tradeFlow/tradeFlowRecordList.jsp").forward(request,response);
    }
}
