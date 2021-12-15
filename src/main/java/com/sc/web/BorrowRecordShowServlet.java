package com.sc.web;

import com.sc.model.Login;
import com.sc.model.Menu;
import com.sc.model.User;
import com.sc.service.*;
import com.sc.service.impl.*;
import com.sc.util.DateFormatUtil;
import com.sc.vo.BorrowVo;
import com.sc.vo.Page;
import com.sc.vo.PurchaseVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "borrowRecordShowServlet", urlPatterns = {"/borrow/showBorrowRecordList"})
public class BorrowRecordShowServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer returnStatus = null;
        if (request.getParameter("returnStatus") != null && !"".equals(request.getParameter("returnStatus"))){
            returnStatus = Integer.valueOf(request.getParameter("returnStatus"));
        }
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String currentPage = request.getParameter("currentPage");
        Integer loginId = (Integer) request.getSession().getAttribute("loginId");
        Page page = new Page();
        if (currentPage != null && !"".equals(currentPage)){
            page.setCurrentPage(Integer.parseInt(currentPage));
        }

        LoginService loginService = new LoginServiceImpl();
        UserService userService = new UserServiceImpl();
        Login login = loginService.queryById(loginId);
        User user = userService.queryById(login.getUserId());

        BorrowVo borrowVo = new BorrowVo();
        if (user.getRoleId() == 2){
            borrowVo.setLoginId(loginId);
        }
        borrowVo.setReturnStatus(returnStatus);
        borrowVo.setStartDate(DateFormatUtil.formatToDate(startDate));
        borrowVo.setEndDate(DateFormatUtil.formatToDate(endDate));

        BorrowService borrowService = new BorrowServiceImpl();
        List<BorrowVo> borrowVoList = borrowService.showBorrowList(borrowVo, page);
        Page pageInfo = borrowService.createPageInfo(borrowVo);
        if (currentPage != null && !"".equals(currentPage)) {
            pageInfo.setCurrentPage(Integer.parseInt(currentPage));
        }
        String url = request.getServletPath().substring(1);
        MenuService menuService = new MenuServiceImpl();
        List<Menu> buttonList = menuService.getButtonList(loginId, url);
        request.setAttribute("buttonList",buttonList);
        request.setAttribute("borrowVoList",borrowVoList);
        request.setAttribute("borrowVoCondition",borrowVo);
        request.setAttribute("startDate",startDate);
        request.setAttribute("endDate",endDate);
        request.setAttribute("pageInfo",pageInfo);

        request.getRequestDispatcher("/WEB-INF/view/borrow/borrowRecordList.jsp").forward(request,response);
    }
}
