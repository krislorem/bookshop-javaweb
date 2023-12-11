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
@WebServlet(name = "admin_user_add", urlPatterns = "/admin/user_add")
public class AdminUserAddServlet extends HttpServlet {
    private final UserService uService = new UserService();

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(@NonNull HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        try {
            BeanUtils.copyProperties(user, request.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.info("IllegalAccessException | InvocationTargetException", e);
        }
        if (uService.register(user)) {
            request.setAttribute("msg", "客户添加成功！");
            request.getRequestDispatcher("/admin/user_list").forward(request, response);
        } else {
            request.setAttribute("failMsg", "用户名或邮箱重复，请重新填写！");
            request.setAttribute("u", user);
            request.getRequestDispatcher("/admin/user_add.jsp").forward(request, response);
        }
    }
}
