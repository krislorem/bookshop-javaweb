package com.example.bookshop.filter;

import org.springframework.lang.NonNull;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @discription: 过滤器: 统一全站编码，以防止出现乱码
 */
@WebFilter(filterName = "EncodeFilter", urlPatterns = "/*")
public class EncodeFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("EncodeFilter处理乱码的过滤器正在初始化--------");
    }

    @Override
    public void doFilter(@NonNull ServletRequest req, ServletResponse resp, @NonNull FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        chain.doFilter(req, resp);           //放行
    }

}
