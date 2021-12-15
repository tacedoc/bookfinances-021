package com.sc.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sc.model.User;
import com.sc.service.UserService;
import com.sc.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "registryPhoneCheckServlet",urlPatterns = {"/registry/phoneExistCheck"})
public class RegistryPhoneCheckServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String phone = request.getParameter("phone");
        UserService userService = new UserServiceImpl();
        User user = userService.checkPhone(phone);

        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(user);

        PrintWriter writer = response.getWriter();
        writer.print(userJson);
        writer.flush();
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
