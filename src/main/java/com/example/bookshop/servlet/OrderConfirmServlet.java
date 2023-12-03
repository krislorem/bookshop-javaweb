package com.example.bookshop.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.example.bookshop.model.*;
import org.apache.commons.beanutils.BeanUtils;
import com.example.bookshop.service.OrderService;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;

@WebServlet(name = "order_confirm",urlPatterns = "/order_confirm")
public class OrderConfirmServlet extends HttpServlet {
    private OrderService oService = new OrderService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Order o = (Order) request.getSession().getAttribute("order");
        try {
            BeanUtils.copyProperties(o, request.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
//        o.setDatetime(new Date());
        o.setDatetime(LocalDateTime.now());
        o.setStatus(2);
        o.setUser((User) request.getSession().getAttribute("user"));
        oService.addOrder(o);
        request.getSession().removeAttribute("order");    //删除属性

        request.setAttribute("msg", "订单支付成功！");
        request.getRequestDispatcher("order_success.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
