package com.example.bookshop.servlet;

import com.example.bookshop.service.UserService;
import lombok.NonNull;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "admin_user_delete", urlPatterns = "/admin/user_delete")
public class AdminUserDeleteServlet extends HttpServlet {
    private final UserService uService = new UserService();

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @NonNull
    protected void doGet(@NonNull HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        boolean isSuccess = uService.delete(id);
        if (isSuccess) {
            request.setAttribute("msg", "客户删除成功");
        } else {
            request.setAttribute("failMsg", "客户有下的订单，请先删除该客户下的订单，再来删除客户！");
        }
        request.getRequestDispatcher("/admin/user_list").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
