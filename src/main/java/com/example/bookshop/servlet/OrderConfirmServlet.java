package com.example.bookshop.servlet;

import com.example.bookshop.model.Order;
import com.example.bookshop.model.User;
import com.example.bookshop.service.OrderService;
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
import java.sql.SQLException;
import java.time.LocalDateTime;

@Slf4j
@WebServlet(name = "order_confirm", urlPatterns = "/order_confirm")
public class OrderConfirmServlet extends HttpServlet {
    private final OrderService oService = new OrderService();

    @Override
    protected void doPost(@NonNull HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Order o = (Order) request.getSession().getAttribute("order");
        try {
            BeanUtils.copyProperties(o, request.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.info("IllegalAccessException | InvocationTargetException", e);
        }
//        o.setDatetime(new Date());
        o.setDatetime(LocalDateTime.now());
        o.setStatus(2);
        o.setUser((User) request.getSession().getAttribute("user"));
        try {
            oService.addOrder(o);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.getSession().removeAttribute("order");    //删除属性

        request.setAttribute("msg", "订单支付成功！");
        request.getRequestDispatcher("order_success.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
