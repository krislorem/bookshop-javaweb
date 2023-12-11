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

@WebServlet(name = "admin_goods_editshow", urlPatterns = "/admin/goods_editshow")
public class AdminGoodsEditshowServelt extends HttpServlet {
    private final GoodsService gService = new GoodsService();

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(@NonNull HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Goods g = gService.getGoodsById(id);
        request.setAttribute("g", g);
        request.getRequestDispatcher("/admin/goods_edit.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(@NonNull HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
