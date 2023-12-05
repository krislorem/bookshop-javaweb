package com.example.bookshop.servlet;

import com.example.bookshop.model.Page;
import com.example.bookshop.model.Type;
import com.example.bookshop.service.GoodsService;
import com.example.bookshop.service.TypeService;
import lombok.NonNull;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "goods_List", urlPatterns = "/goods_list")
public class GoodsListServlet extends HttpServlet {
    private final GoodsService gService = new GoodsService();
    private final TypeService tService = new TypeService();

    @NonNull
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(@NonNull HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = 0;
        if (request.getParameter("typeid") != null) {
            id = Integer.parseInt(request.getParameter("typeid"));
        }
        int pageNumber = 1;
        if (request.getParameter("pageNumber") != null) {
            try {
                pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
            } catch (Exception ignored) {

            }

        }
        Type t = null;
        if (id != 0) {
            t = tService.selectTypeNameByID(id);
        }
        request.setAttribute("t", t);

        if (pageNumber <= 0)
            pageNumber = 1;
        Page p = gService.selectPageByTypeID(id, pageNumber);

        if (p.getTotalPage() == 0) {
            p.setTotalPage(1);
            p.setPageNumber(1);
        } else {
            if (pageNumber >= p.getTotalPage() + 1) {
                p = gService.selectPageByTypeID(id, p.getTotalPage());
            }
        }

        request.setAttribute("p", p);
        request.setAttribute("id", String.valueOf(id));
        request.getRequestDispatcher("/goods_list.jsp").forward(request, response);
    }
}
