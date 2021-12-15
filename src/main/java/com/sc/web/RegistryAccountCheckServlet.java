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
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "registryAccountCheckServlet",urlPatterns = {"/registry/accountExistCheck"})
public class RegistryAccountCheckServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String account = request.getParameter("account");
        LoginService loginService = new LoginServiceImpl();
        Login login = loginService.checkAccount(account);

        ObjectMapper objectMapper = new ObjectMapper();
        String loginJson = objectMapper.writeValueAsString(login);

        PrintWriter writer = response.getWriter();
        writer.print(loginJson);
        writer.flush();
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
