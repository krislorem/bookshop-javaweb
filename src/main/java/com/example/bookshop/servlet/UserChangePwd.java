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

@WebServlet(name = "user_changepwd", urlPatterns = "/user_changepwd")
public class UserChangePwd extends HttpServlet {

    private final UserService uService = new UserService();

    @Override
    protected void doPost(@NonNull HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password = request.getParameter("password");
        String newPwd = request.getParameter("newPassword");

        User u = (User) request.getSession().getAttribute("user");
        if (password.equals(u.getPassword())) {
            u.setPassword(newPwd);
            uService.updatePwd(u);
            request.setAttribute("msg", "修改成功！");
            request.getRequestDispatcher("/user_center.jsp").forward(request, response);
        } else {
            request.setAttribute("failMsg", "修改失败，原密码不正确，你再想想！");
            request.getRequestDispatcher("/user_center.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        this.doPost(request, response);
    }
}
