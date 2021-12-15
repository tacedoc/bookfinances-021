package com.sc.web;

import com.sc.service.UserService;
import com.sc.service.impl.UserServiceImpl;
import com.sc.vo.Page;
import com.sc.vo.UserVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "userInfoListShowServlet", urlPatterns = {"/user/userInfoListShow"})
public class UserInfoListShowServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String account = request.getParameter("account");
        String phone = request.getParameter("phone");
        String status = request.getParameter("available");
        Boolean available = null;
        if (status != null && !"".equals(status)){
            available = Boolean.valueOf(status);
        }
        String currentPage = request.getParameter("currentPage");
        Page page = new Page();
        if (currentPage != null && !"".equals(currentPage)){
            page.setCurrentPage(Integer.parseInt(currentPage));
        }
        UserVo userVo = new UserVo();
        userVo.setAccount(account);
        userVo.setPhone(phone);
        userVo.setAvailable(available);

        UserService userService = new UserServiceImpl();
        List<UserVo> userVoList = userService.showUserList(userVo, page);
        Page pageInfo = userService.createPageInfo(userVo);
        if (currentPage != null && !"".equals(currentPage)) {
            pageInfo.setCurrentPage(Integer.parseInt(currentPage));
        }

        request.setAttribute("userInfoList",userVoList);
        request.setAttribute("userVoCondition",userVo);
        request.getRequestDispatcher("/WEB-INF/view/user/userList.jsp").forward(request,response);

    }
}
