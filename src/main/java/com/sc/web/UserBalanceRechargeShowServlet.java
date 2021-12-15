package com.sc.web;

import com.sc.model.Login;
import com.sc.model.User;
import com.sc.service.LoginService;
import com.sc.service.UserService;
import com.sc.service.impl.LoginServiceImpl;
import com.sc.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "userBalanceRechargeShowServlet", urlPatterns = {"/user/rechargeBalanceShow"})
public class UserBalanceRechargeShowServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer loginId =(Integer) request.getSession().getAttribute("loginId");

        LoginService loginService = new LoginServiceImpl();
        UserService userService = new UserServiceImpl();
        Login login = loginService.queryById(loginId);
        User user = userService.queryById(login.getUserId());

        request.setAttribute("availableBalance",user.getBalance());

        request.getRequestDispatcher("/WEB-INF/view/user/rechargeBalance.jsp").forward(request,response);
    }
}
