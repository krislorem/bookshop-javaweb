package com.example.bookshop.servlet;

import com.example.bookshop.model.Page;
import com.example.bookshop.service.UserService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet(name = "admin_user_list", urlPatterns = "/admin/user_list")
public class AdminUserListServlet extends HttpServlet {
    private final UserService uService = new UserService();

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(@NonNull HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNumber = 1;
        if (request.getParameter("pageNumber") != null) {
            try {
                pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
            } catch (Exception e) {
                log.info("Exception", e);
            }
        }
        if (pageNumber <= 0)
            pageNumber = 1;
        Page p = uService.getUserPage(pageNumber);
        if (p.getTotalPage() == 0) {
            p.setTotalPage(1);
            p.setPageNumber(1);
        } else {
            if (pageNumber >= p.getTotalPage() + 1) {
                p = uService.getUserPage(pageNumber);
            }
        }
        request.setAttribute("p", p);
        request.getRequestDispatcher("/admin/user_list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
