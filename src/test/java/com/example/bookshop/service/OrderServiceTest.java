package com.example.bookshop.service;

import com.example.bookshop.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

@Slf4j
class OrderServiceTest {
OrderService service = new OrderService();
    @Test
    void addOrder() throws SQLException {
        service.addOrder(new Order());
    }

    @Test
    void selectAll() {
        service.selectAll(1).forEach(System.out::println);
    }

    @Test
    void getOrderPage() {
       log.info(service.getOrderPage(2,2).toString());
    }

    @Test
    void updateStatus() {
    }

    @Test
    void delete() {
        service.delete(25);
    }
}