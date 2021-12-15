package com.sc.web;

import com.fasterxml.jackson.databind.ObjectMapper;
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

@WebServlet(name = "loginVerifyServlet",urlPatterns = {"/login/loginVerify"})
public class LoginVerifyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获得登录界面传过来的账号与密码信息
        String account = request.getParameter("account");
        String password = request.getParameter("password");

        LoginService loginService = new LoginServiceImpl();
        Login login = loginService.checkAccountAndPassword(account,password);

        //判断是否有账户，有账户此时设置一个session，用来做权限控制
        if (login != null){
            HttpSession session = request.getSession();
            session.setAttribute("loginId",login.getId());
        }

        //将登录账号信息包装成json数据(如果数据不存在,json数据为空字符串)
        ObjectMapper objectMapper = new ObjectMapper();
        String loginJson = objectMapper.writeValueAsString(login);

        //将登录账号信息以json格式的数据发送给前端
        PrintWriter writer = response.getWriter();
        writer.print(loginJson);
        writer.flush();
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
