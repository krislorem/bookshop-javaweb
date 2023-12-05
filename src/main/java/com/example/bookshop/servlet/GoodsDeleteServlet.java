package com.example.bookshop.servlet;

import com.example.bookshop.model.Order;
import lombok.NonNull;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "goods_delete", urlPatterns = "/goods_delete")
public class GoodsDeleteServlet extends HttpServlet {
    @Override
    protected void doPost(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response) throws ServletException, IOException {
        Order o = (Order) request.getSession().getAttribute("order");
        int goodsid = Integer.parseInt(request.getParameter("goodsid"));
        o.delete(goodsid);
        response.getWriter().print("ok");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
