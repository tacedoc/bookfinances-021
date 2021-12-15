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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "registryServlet", urlPatterns = {"/registry"})
public class RegistryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String phone = request.getParameter("phone");
        String account = request.getParameter("account");
        String password= request.getParameter("password");
        String username = request.getParameter("username");

        User user = new User();
        user.setPhone(phone);
        user.setUsername(username);
        user.setBalance(1.00);//为刚注册的账户送一元注册金
        user.setRoleId(2);//注册的用户都为借阅者的角色
        Login login = new Login();
        login.setAccount(account);
        login.setPassword(password);
        login.setAvailable(true);

        LoginService loginService = new LoginServiceImpl();
        Boolean result = loginService.registry(login, user);

        String msg = "success";
        //注册成功，账户此时设置一个session，用来做权限控制
        if (result){
            Login loginInfo = loginService.checkAccount(login.getAccount());
            HttpSession session = request.getSession();
            session.setAttribute("loginId",loginInfo.getId());
        }else {
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
