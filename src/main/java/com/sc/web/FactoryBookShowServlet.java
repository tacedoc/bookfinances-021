package com.sc.web;

import com.sc.model.FactoryBook;
import com.sc.model.Menu;
import com.sc.service.FactoryBookService;
import com.sc.service.MenuService;
import com.sc.service.impl.FactoryBookServiceImpl;
import com.sc.service.impl.MenuServiceImpl;
import com.sc.vo.Page;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "factoryBookShowServlet",urlPatterns = {"/factoryBook/showFactoryBookList"})
public class FactoryBookShowServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String factoryBookName = request.getParameter("factoryBookName");
        String author = request.getParameter("author");
        String factoryName = request.getParameter("factoryName");
        String currentPage = request.getParameter("currentPage");
        Integer loginId = (Integer) request.getSession().getAttribute("loginId");
        String url = request.getServletPath().substring(1);
        Page page = new Page();
        if (currentPage != null && !"".equals(currentPage)){
            page.setCurrentPage(Integer.parseInt(currentPage));
        }
        FactoryBook factoryBook = new FactoryBook();
        factoryBook.setName(factoryBookName);
        factoryBook.setAuthor(author);
        factoryBook.setFactoryName(factoryName);

        FactoryBookService factoryBookService = new FactoryBookServiceImpl();
        List<FactoryBook> factoryBookList = factoryBookService.showFactoryBookList(factoryBook,page);
        Page pageInfo = factoryBookService.createPageInfo(factoryBook);
        if (currentPage != null && !"".equals(currentPage)){
            pageInfo.setCurrentPage(Integer.parseInt(currentPage));
        }
        MenuService menuService = new MenuServiceImpl();
        List<Menu> buttonList = menuService.getButtonList(loginId, url);

        request.setAttribute("buttonList",buttonList);
        request.setAttribute("factoryBookList",factoryBookList);
        request.setAttribute("factoryBook",factoryBook);
        request.setAttribute("pageInfo",pageInfo);

        request.getRequestDispatcher("/WEB-INF/view/purchase/factoryBookList.jsp").forward(request,response);
    }

}
