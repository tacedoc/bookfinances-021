package com.sc.web;

import com.sc.model.Login;
import com.sc.model.Menu;
import com.sc.model.User;
import com.sc.service.LoginService;
import com.sc.service.MenuService;
import com.sc.service.RoleMenuService;
import com.sc.service.UserService;
import com.sc.service.impl.LoginServiceImpl;
import com.sc.service.impl.MenuServiceImpl;
import com.sc.service.impl.RoleMenuServiceImpl;
import com.sc.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "loginShowIndexServlet",urlPatterns = "/login/showIndex")
public class LoginShowIndexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer loginId =(Integer) request.getSession().getAttribute("loginId");

        LoginService loginService = new LoginServiceImpl();
        UserService userService = new UserServiceImpl();
        MenuService menuService = new MenuServiceImpl();
        Login login = loginService.queryById(loginId);
        User user = userService.queryById(login.getUserId());
        List<Menu> menuList = menuService.getMenuList(user.getRoleId());

        request.setAttribute("username",user.getUsername());
        request.setAttribute("menuList",menuList);

        request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request,response);
    }
}
