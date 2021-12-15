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

@WebServlet(name = "userInfoEditServlet", urlPatterns = {"/user/userInfoEdit"})
public class UserInfoEditServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer loginId =(Integer) request.getSession().getAttribute("loginId");
        String username = request.getParameter("username");
        String phone = request.getParameter("phone");

        LoginService loginService = new LoginServiceImpl();
        UserService userService = new UserServiceImpl();
        Login login = loginService.queryById(loginId);
        User user = userService.queryById(login.getUserId());
        User newUser = userService.checkPhone(phone);
        String msg = "success";
        if (newUser != null && newUser.getId() != user.getId()){
            msg = "手机号已存在！修改失败！";
        }else {
            user.setUsername(username);
            user.setPhone(phone);
            userService.update(user);
        }
        PrintWriter writer = response.getWriter();
        writer.print(msg);
        writer.flush();
        writer.close();

    }
}
