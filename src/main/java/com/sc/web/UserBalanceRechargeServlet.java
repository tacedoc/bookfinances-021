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
import java.io.PrintWriter;

@WebServlet(name = "userBalanceRechargeServlet", urlPatterns = {"/user/rechargeBalance"})
public class UserBalanceRechargeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer loginId =(Integer) request.getSession().getAttribute("loginId");
        Double balance = Double.valueOf(request.getParameter("balance"));

        UserService userService = new UserServiceImpl();
        Boolean result = userService.rechargeBalance(loginId, balance);
        String msg = "success";
        if (!result){
            msg = "failure";
        }
        PrintWriter writer = response.getWriter();
        writer.print(msg);
        writer.flush();
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
