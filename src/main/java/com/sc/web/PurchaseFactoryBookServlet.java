package com.sc.web;

import com.sc.model.Book;
import com.sc.model.Login;
import com.sc.model.Purchase;
import com.sc.service.LoginService;
import com.sc.service.PurchaseService;
import com.sc.service.impl.LoginServiceImpl;
import com.sc.service.impl.PurchaseServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(name = "PurchaseFactoryBookServlet",urlPatterns = "/purchase/purchaseFactoryBook")
public class PurchaseFactoryBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String factoryBookName = request.getParameter("factoryBookName");
        String author = request.getParameter("author");
        Double unitPrice = Double.valueOf(request.getParameter("unitPrice"));
        String factoryName = request.getParameter("factoryName");
        Integer count = Integer.valueOf(request.getParameter("count"));
        Double sellPrice = Double.valueOf(request.getParameter("sellPrice"));
        Double borrowPrice = Double.valueOf(request.getParameter("borrowPrice"));
        Integer loginId =(Integer) request.getSession().getAttribute("loginId");

        Book book = new Book();
        book.setName(factoryBookName);
        book.setAuthor(author);
        book.setVendor(factoryName);
        book.setUnitPrice(sellPrice);
        book.setBorrowPrice(borrowPrice);
        book.setDelFlag(false);
        Purchase purchase = new Purchase();
        purchase.setLoginId(loginId);
        purchase.setCount(count);
        purchase.setUnitPrice(unitPrice);
        purchase.setPurchaseDate(new Date());

        PurchaseService purchaseService = new PurchaseServiceImpl();
        Boolean res = purchaseService.addPurchaseRecord(book, purchase);
        String msg = "success";
        if (!res){
            msg = "failure";
        }

        PrintWriter writer = response.getWriter();
        writer.print(msg);
        writer.flush();
        writer.close();
    }
}
