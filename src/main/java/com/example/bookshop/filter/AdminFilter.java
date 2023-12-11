package com.example.bookshop.filter;

import com.example.bookshop.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @discription: 处理非法访问后台 "/admin/*"
 */
@WebFilter(filterName = "AdminFilter", urlPatterns = "/admin/*")
public class AdminFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //基于HTTP
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse requestp = (HttpServletResponse) resp;
        User u = (User) request.getSession().getAttribute("user");
        //用户为空 或 不是管理员
        if (u == null || !u.isIsadmin()) {
            requestp.sendRedirect("../index");
        } else {
            //放行资源
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void init(FilterConfig config) {

    }

}
