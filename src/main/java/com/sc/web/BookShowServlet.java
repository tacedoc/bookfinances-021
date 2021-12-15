package com.sc.web;

import com.sc.model.Book;
import com.sc.model.Login;
import com.sc.model.Menu;
import com.sc.model.User;
import com.sc.service.BookService;
import com.sc.service.LoginService;
import com.sc.service.MenuService;
import com.sc.service.UserService;
import com.sc.service.impl.BookServiceImpl;
import com.sc.service.impl.LoginServiceImpl;
import com.sc.service.impl.MenuServiceImpl;
import com.sc.service.impl.UserServiceImpl;
import com.sc.vo.Page;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "bookShowServlet",urlPatterns = "/book/showBookList")
public class BookShowServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookName = request.getParameter("bookName");
        String author = request.getParameter("author");
        String vendor = request.getParameter("vendor");
        Boolean delFlag = Boolean.valueOf(request.getParameter("delFlag"));
        Integer loginId = (Integer) request.getSession().getAttribute("loginId");
        String url = request.getServletPath().substring(1);
        String currentPage = request.getParameter("currentPage");
        Page page = new Page();
        if (currentPage != null && !"".equals(currentPage)){
            page.setCurrentPage(Integer.parseInt(currentPage));
        }
        Book book = new Book();
        book.setName(bookName);
        book.setAuthor(author);
        book.setVendor(vendor);
        book.setDelFlag(delFlag);
        if (delFlag){
            url=url+"?delFlag=true";
        }else {
            url=url+"?delFlag=false";
        }
        BookService bookService = new BookServiceImpl();
        List<Book> bookList = bookService.showBookList(book,page);
        Page pageInfo = bookService.createPageInfo(book);
        if (currentPage != null && !"".equals(currentPage)) {
            pageInfo.setCurrentPage(Integer.parseInt(currentPage));
        }
        MenuService menuService = new MenuServiceImpl();
        List<Menu> buttonList = menuService.getButtonList(loginId, url);
        request.setAttribute("buttonList",buttonList);
        request.setAttribute("delFlag",delFlag);
        request.setAttribute("bookList",bookList);
        request.setAttribute("bookCondition",book);
        request.setAttribute("pageInfo",pageInfo);

        request.getRequestDispatcher("/WEB-INF/view/book/bookList.jsp").forward(request,response);
    }
}
