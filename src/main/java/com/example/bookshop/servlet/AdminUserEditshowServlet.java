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

@WebServlet(name = "admin_user_editshow", urlPatterns = "/admin/user_editshow")
public class AdminUserEditshowServlet extends HttpServlet {
    private final UserService uService = new UserService();

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(@NonNull HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = uService.selectById(id);
        request.setAttribute("u", user);
        request.getRequestDispatcher("/admin/user_edit.jsp").forward(request, response);
    }
}
