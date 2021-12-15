package com.sc.web;

import com.sc.model.Login;
import com.sc.service.LoginService;
import com.sc.service.impl.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "userEditPasswordServlet", urlPatterns = {"/user/editPassword"})
public class UserEditPasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        Integer loginId =(Integer) request.getSession().getAttribute("loginId");

        LoginService loginService = new LoginServiceImpl();
        Login login = loginService.queryById(loginId);
        String msg = "success";
        if (login.getPassword().equals(oldPassword)){
            login.setPassword(newPassword);
            loginService.update(login);
        }else{
            msg = "failure";
        }

        PrintWriter writer = response.getWriter();
        writer.print(msg);
        writer.flush();
        writer.close();

    }
}
