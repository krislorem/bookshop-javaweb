package com.example.bookshop.servlet;

import com.example.bookshop.service.OrderService;
import org.springframework.lang.NonNull;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "admin_order_delete", urlPatterns = "/admin/order_delete")
public class AdminOrderDeleteServlet extends HttpServlet {
    private final OrderService oService = new OrderService();

    @Override
    protected void doGet(@NonNull HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        oService.delete(id);   //删除订单
        request.getRequestDispatcher("/admin/order_list").forward(request, response);
    }
}
