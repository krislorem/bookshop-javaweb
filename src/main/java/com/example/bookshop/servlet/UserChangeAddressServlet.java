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
@WebServlet(name = "user_changeaddress", urlPatterns = "/user_changeaddress")
public class UserChangeAddressServlet extends HttpServlet {

    private final UserService uService = new UserService();

    @Override
    protected void doPost(@NonNull HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User loginUser = (User) request.getSession().getAttribute("user");

        try {
            BeanUtils.copyProperties(loginUser, request.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.info("IllegalAccessException | InvocationTargetException", e);
        }
        uService.updateUserAddress(loginUser);
        request.setAttribute("msg", "收件信息更新成功！");
        request.getRequestDispatcher("/user_center.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
