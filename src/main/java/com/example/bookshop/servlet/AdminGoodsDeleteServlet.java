package com.example.bookshop.servlet;

import com.example.bookshop.service.GoodsService;
import org.springframework.lang.NonNull;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "admin_goods_delete", urlPatterns = "/admin/goods_delete")
public class AdminGoodsDeleteServlet extends HttpServlet {
    private final GoodsService gService = new GoodsService();

    @Override
    protected void doPost(@NonNull HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        gService.delete(id);
        request.getRequestDispatcher("/admin/goods_list").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
