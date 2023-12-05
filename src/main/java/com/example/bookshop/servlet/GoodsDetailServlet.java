package com.example.bookshop.servlet;

import com.example.bookshop.model.Goods;
import com.example.bookshop.service.GoodsService;
import lombok.NonNull;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "goods_detail", urlPatterns = "/goods_detail")
public class GoodsDetailServlet extends HttpServlet {

    private final GoodsService gService = new GoodsService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(@NonNull HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Goods g = gService.getGoodsById(id);
        request.setAttribute("g", g);
        request.getRequestDispatcher("/goods_detail.jsp").forward(request, response);
    }
}
