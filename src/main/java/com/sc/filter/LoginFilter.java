package com.sc.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebFilter(filterName = "loginFilter",urlPatterns = {"/*"})
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();
        Object loginId = session.getAttribute("loginId");
        String path = request.getServletPath();
        System.out.println("request.getServletPath():"+request.getServletPath());
        System.out.println("request.getContextPath()():"+request.getContextPath());
        System.out.println("request.getRequestURI():"+request.getRequestURI());
        System.out.println("request.getRequestURL():"+request.getRequestURL());


        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        //不需要过滤的url
        List<String> unFilterUrlList = Arrays.asList(".js",".css",".ico",".jpg",".png","/login.jsp","/login/loginVerify","/registry");
        boolean flag = true;
        for (String url : unFilterUrlList) {
            if (path.contains(url)) {
                flag =false;
                break;
            }
        }
        if (flag) {
            if (loginId != null) {
                chain.doFilter(request, response);
            }else {
                response.sendRedirect(request.getContextPath()+"/login.jsp");
            }
        }else{
            chain.doFilter(request, response);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
