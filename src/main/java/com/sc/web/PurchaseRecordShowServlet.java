package com.sc.web;

import com.sc.service.PurchaseService;
import com.sc.service.impl.PurchaseServiceImpl;
import com.sc.util.DateFormatUtil;
import com.sc.vo.Page;
import com.sc.vo.PurchaseVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "purchaseRecordShowServlet", urlPatterns = {"/purchase/showPurchaseRecordList"})
public class PurchaseRecordShowServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String purchaser = request.getParameter("purchaser");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String currentPage = request.getParameter("currentPage");
        Page page = new Page();
        if (currentPage != null && !"".equals(currentPage)){
            page.setCurrentPage(Integer.parseInt(currentPage));
        }
        PurchaseVo purchaseVo = new PurchaseVo();
        purchaseVo.setPurchaser(purchaser);
        purchaseVo.setStartDate(DateFormatUtil.formatToDate(startDate));
        purchaseVo.setEndDate(DateFormatUtil.formatToDate(endDate));
        PurchaseService purchaseService = new PurchaseServiceImpl();
        List<PurchaseVo> purchaseVoList = purchaseService.showPurchaseVoList(purchaseVo, page);
        Page pageInfo = purchaseService.createPageInfo(purchaseVo);
        if (currentPage != null && !"".equals(currentPage)) {
            pageInfo.setCurrentPage(Integer.parseInt(currentPage));
        }

        request.setAttribute("purchaseVoList",purchaseVoList);
        request.setAttribute("purchaseVoCondition",purchaseVo);
        request.setAttribute("startDate",startDate);
        request.setAttribute("endDate",endDate);
        request.setAttribute("pageInfo",pageInfo);

        request.getRequestDispatcher("/WEB-INF/view/purchase/purchaseRecordList.jsp").forward(request,response);
    }
}
