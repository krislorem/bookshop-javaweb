package com.example.bookshop.servlet;

import com.example.bookshop.model.User;
import com.example.bookshop.service.UserService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@Slf4j
@WebServlet(name = "admin_user_reset", urlPatterns = "/admin/user_reset")
public class AdminUserResetServlet extends HttpServlet {
    private final UserService uService = new UserService();

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(@NonNull HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User u = new User();
        try {
            BeanUtils.copyProperties(u, request.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.info("IllegalAccessException | InvocationTargetException", e);
        }
        uService.updatePwd(u);
        request.getRequestDispatcher("/admin/user_list").forward(request, response);
    }
}
