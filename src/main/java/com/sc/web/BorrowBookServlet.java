package com.sc.web;

import com.sc.model.Book;
import com.sc.model.Borrow;
import com.sc.model.Purchase;
import com.sc.service.BorrowService;
import com.sc.service.PurchaseService;
import com.sc.service.impl.BorrowServiceImpl;
import com.sc.service.impl.PurchaseServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(name = "borrowBookServlet", urlPatterns = {"/borrow/borrowBook"})
public class BorrowBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookId = request.getParameter("bookId");
        String promiseDay = request.getParameter("promiseDay");
        Integer loginId = (Integer) request.getSession().getAttribute("loginId");

        Borrow borrow = new Borrow();
        borrow.setLoginId(loginId);
        borrow.setBookId(Integer.valueOf(bookId));
        borrow.setBeginDate(new Date());
        borrow.setPromiseDay(Integer.valueOf(promiseDay));
        borrow.setReturnStatus(0);

        BorrowService borrowService = new BorrowServiceImpl();
        Boolean res = borrowService.insert(borrow);

        String msg = "success";
        if (!res) {
            msg = "借阅失败！";
        }

        PrintWriter writer = response.getWriter();
        writer.print(msg);
        writer.flush();
        writer.close();
    }
}
