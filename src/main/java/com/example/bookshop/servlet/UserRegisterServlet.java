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

/**
 * @className: UserRegisterServlet
 * @author: ZhaiJinPei
 * @discription: 用户注册操作
 * @version: 1.0.2
 */
@Slf4j
@WebServlet(name = "user_register", urlPatterns = "/user_rigister")
public class UserRegisterServlet extends HttpServlet {
    private final UserService uService = new UserService();

    @Override
    protected void doPost(@NonNull HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        try {
            //将所有信息封装到 user对象中
            BeanUtils.copyProperties(user, request.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.info("IllegalAccessException | InvocationTargetException ");
        }

        //调用service层(业务逻辑层)的方法完成注册操作
        if (uService.register(user)) {
            request.setAttribute("msg", "注册成功，请登录！");
            request.getRequestDispatcher("user_login.jsp").forward(request, response);
        } else {
            request.setAttribute("msg", "用户名或邮箱重复，请重新填写！");
            request.getRequestDispatcher("user_register.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
