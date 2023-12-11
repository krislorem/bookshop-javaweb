package com.example.bookshop.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
class OrderServiceTest {
OrderService service = new OrderService();
    @Test
    void addOrder() {

    }

    @Test
    void selectAll() {
        service.selectAll(1).forEach(System.out::println);
    }

    @Test
    void getOrderPage() {

    }

    @Test
    void updateStatus() {
    }

    @Test
    void delete() {
    }
}