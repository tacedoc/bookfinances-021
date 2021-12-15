package com.sc.web;

import com.sc.model.Borrow;
import com.sc.model.Login;
import com.sc.model.User;
import com.sc.service.BorrowService;
import com.sc.service.LoginService;
import com.sc.service.UserService;
import com.sc.service.impl.BorrowServiceImpl;
import com.sc.service.impl.LoginServiceImpl;
import com.sc.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "borrowVerifyServlet", urlPatterns = {"/borrow/borrowVerify"})
public class BorrowVerifyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookId = request.getParameter("bookId");
        String promiseDay = request.getParameter("promiseDay");
        Integer loginId = (Integer) request.getSession().getAttribute("loginId");

        Borrow borrow = new Borrow();
        borrow.setLoginId(loginId);
        borrow.setBookId(Integer.valueOf(bookId));
        borrow.setPromiseDay(Integer.valueOf(promiseDay));

        BorrowService borrowService = new BorrowServiceImpl();
        Boolean borrowVerify = borrowService.borrowVerify(borrow);

        if (borrowVerify){
            request.getRequestDispatcher("/borrow/borrowBook").forward(request,response);
        }else {

            PrintWriter writer = response.getWriter();
            writer.print("借阅失败，账户资金不够或有未处理的逾期记录！");
            writer.flush();
            writer.close();
        }


    }
}
