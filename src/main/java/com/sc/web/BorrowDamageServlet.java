package com.sc.web;

import com.sc.service.BorrowService;
import com.sc.service.impl.BorrowServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "borrowDamageServlet", urlPatterns = {"/borrow/bookDamage"})
public class BorrowDamageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer borrowId = Integer.valueOf(request.getParameter("borrowId"));

        BorrowService borrowService = new BorrowServiceImpl();
        Boolean res = borrowService.bookDamage(borrowId);
        String msg = "success";
        if (!res){
            msg = "报损失败！";
        }
        PrintWriter writer = response.getWriter();
        writer.print(msg);
        writer.flush();
        writer.close();
    }
}
