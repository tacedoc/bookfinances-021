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

@WebServlet(name = "borrowPostponeServlet", urlPatterns = {"/borrow/bookPostpone"})
public class BorrowPostponeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer borrowId = Integer.valueOf(request.getParameter("borrowId"));
        Integer postponeDays = Integer.valueOf(request.getParameter("postponeDays"));

        BorrowService borrowService = new BorrowServiceImpl();
        Boolean res = borrowService.bookPostpone(borrowId, postponeDays);
        String msg = "success";
        if (!res){
          msg = "延期失败!";
        }
        PrintWriter writer = response.getWriter();
        writer.print(msg);
        writer.flush();
        writer.close();
    }
}
