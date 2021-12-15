package com.sc.web;

import com.sc.model.Book;
import com.sc.service.BookService;
import com.sc.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "bookDownServlet", urlPatterns = {"/book/bookDown"})
public class BookDownServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer bookId = Integer.valueOf(request.getParameter("bookId"));

        Book book = new Book();
        book.setId(bookId);
        book.setDelFlag(true);

        BookService bookService = new BookServiceImpl();
        Boolean res = bookService.update(book);
        String msg = "success";
        if (!res){
            msg = "下架失败！";
        }
        PrintWriter writer = response.getWriter();
        writer.print(msg);
        writer.flush();
        writer.close();
    }
}
