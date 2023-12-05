package com.example.bookshop.servlet;

import com.example.bookshop.model.Page;
import com.example.bookshop.service.GoodsService;
import org.springframework.lang.NonNull;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "goods_search", urlPatterns = "/goods_search")
public class GoodsSearchServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private final GoodsService gService = new GoodsService();

    @Override
    protected void doGet(@NonNull HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        int pageNumber = 1;
        if (request.getParameter("pageNumber") != null) {
            try {
                pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
            } catch (Exception ignored) {

            }
        }
        if (pageNumber <= 0) {
            pageNumber = 1;
        }
        Page p = gService.getSearchGoodsPage(keyword, pageNumber);

        if (p.getTotalPage() == 0) {
            p.setTotalPage(1);
            p.setPageNumber(1);
        } else {
            if (pageNumber >= p.getTotalPage() + 1) {
                p = gService.getSearchGoodsPage(keyword, pageNumber);
            }
        }
        request.setAttribute("p", p);
        request.setAttribute("keyword", URLEncoder.encode(keyword, StandardCharsets.UTF_8));
        request.getRequestDispatcher("/goods_search.jsp").forward(request, response);
    }

}
