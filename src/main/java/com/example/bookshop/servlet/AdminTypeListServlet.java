package com.example.bookshop.servlet;

import com.example.bookshop.model.Type;
import com.example.bookshop.service.TypeService;
import lombok.NonNull;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "admin_type_list", urlPatterns = "/admin/type_list")
public class AdminTypeListServlet extends HttpServlet {
    private final TypeService tService = new TypeService();

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(@NonNull HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Type> list = tService.GetAllType();
        request.setAttribute("list", list);
        this.getServletContext().removeAttribute("typeList");
        this.getServletContext().setAttribute("typeList", list);
        request.getRequestDispatcher("/admin/type_list.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(@NonNull HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
