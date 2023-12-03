package com.example.bookshop.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.example.bookshop.service.OrderService;

import java.io.IOException;

@WebServlet(name = "admin_order_status",urlPatterns = "/admin/order_status")
public class AdminOrderStatusServlet extends HttpServlet {
    private OrderService oService = new OrderService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int status = Integer.parseInt(request.getParameter("status"));
        oService.updateStatus(id, status);
//        response.sendRedirect("/admin/order_list?status="+status);     //重定向 不带参
        request.getRequestDispatcher("/admin/order_list?status="+status).forward(request,response);  //请求转发 传参
    }
}
