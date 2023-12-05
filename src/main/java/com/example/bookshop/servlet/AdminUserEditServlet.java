package com.example.bookshop.servlet;

import com.example.bookshop.model.User;
import com.example.bookshop.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet(name = "admin_user_edit", urlPatterns = "/admin/user_edit")
public class AdminUserEditServlet extends HttpServlet {
    private final UserService uService = new UserService();

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User u = new User();
        try {
            BeanUtils.copyProperties(u, request.getParameterMap());
        } catch (Exception e) {
            log.info("Exception", e);
        }
        uService.updateUserAddress(u);
        request.getRequestDispatcher("/admin/user_list").forward(request, response);
    }
}
