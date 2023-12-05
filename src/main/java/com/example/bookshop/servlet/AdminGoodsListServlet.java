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

@WebServlet(name = "admin_goods_list", urlPatterns = "/admin/goods_list")
public class AdminGoodsListServlet extends HttpServlet {
    private final GoodsService gService = new GoodsService();

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(@NonNull HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int type = 0;//推荐类型
        if (request.getParameter("type") != null) {
            type = Integer.parseInt(request.getParameter("type"));
        }
        int pageNumber = 1;
        if (request.getParameter("pageNumber") != null) {
            try {
                pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
            } catch (Exception ignored) {

            }

        }
        if (pageNumber <= 0)
            pageNumber = 1;
        Page p = gService.getGoodsRecommendPage(type, pageNumber);
        if (p.getTotalPage() == 0) {
            p.setTotalPage(1);
            p.setPageNumber(1);
        } else {
            if (pageNumber >= p.getTotalPage() + 1) {
                p = gService.getGoodsRecommendPage(type, pageNumber);
            }
        }
        request.setAttribute("p", p);
        request.setAttribute("type", type);
        request.getRequestDispatcher("/admin/goods_list.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
