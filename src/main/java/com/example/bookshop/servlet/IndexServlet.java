package com.example.bookshop.servlet;

import com.example.bookshop.service.GoodsService;
import lombok.NonNull;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "IndexServlet", urlPatterns = "/index")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private final GoodsService gService = new GoodsService();

    @Override
    protected void doGet(@NonNull HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Map<String, Object>> ScrollGood = gService.getGoodsList(1);
        request.setAttribute("scroll", ScrollGood);

        List<Map<String, Object>> newList = gService.getGoodsList(3);
        request.setAttribute("newList", newList);

        List<Map<String, Object>> hotList = gService.getGoodsList(2);
        request.setAttribute("hotList", hotList);

        //response.sendRedirect("index.jsp");
        request.getRequestDispatcher("index.jsp").forward(request, response);


    }
}
