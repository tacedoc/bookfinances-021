package com.sc.web;

import com.sc.model.Login;
import com.sc.service.LoginService;
import com.sc.service.impl.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "loginToEnableServlet", urlPatterns = {"/login/toEnable"})
public class LoginToEnableServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer loginId = Integer.valueOf(request.getParameter("loginId"));

        Login login = new Login();
        login.setId(loginId);
        login.setAvailable(true);

        LoginService loginService = new LoginServiceImpl();
        Boolean res = loginService.update(login);
        String msg = "success";
        if (!res){
            msg = "failure";
        }
        PrintWriter writer = response.getWriter();
        writer.print(msg);
        writer.flush();
        writer.close();
    }
}
