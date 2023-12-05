package com.example.bookshop.servlet;

import com.example.bookshop.model.User;
import com.example.bookshop.service.UserService;
import lombok.NonNull;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "user_login", urlPatterns = "/user_login")
public class UserLoginServlet extends HttpServlet {
    private final UserService uService = new UserService();

    @Override
    protected void doPost(@NonNull HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ue = request.getParameter("ue");
        String password = request.getParameter("password");
        User user = uService.login(ue, password);
        if (user == null) {
            request.setAttribute("failMsg", "用户名、邮箱或者密码错误，请重新登录！");
            request.getRequestDispatcher("user_login.jsp").forward(request, response);
        } else {
            request.getSession().setAttribute("user", user);
            request.getRequestDispatcher("user_center.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
