package com.example.bookshop.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.example.bookshop.model.User;
import org.apache.commons.beanutils.BeanUtils;
import com.example.bookshop.service.UserService;

import java.io.IOException;

@WebServlet(name = "admin_user_edit",urlPatterns = "/admin/user_edit")
public class AdminUserEditServlet extends HttpServlet {
    private UserService uService = new UserService();
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User u = new User();
        try {
            BeanUtils.copyProperties(u, request.getParameterMap());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        uService.updateUserAddress(u);
        request.getRequestDispatcher("/admin/user_list").forward(request, response);
    }
}
