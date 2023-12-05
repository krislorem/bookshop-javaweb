package com.example.bookshop.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

@Slf4j
class UserServiceTest {
    UserService service = new UserService();
    @BeforeAll
    static void setUp() {
        log.info("UserServiceTest set up");
    }

    @AfterAll
    static void tearDown() {
        log.info("UserServiceTest tear down");
    }

    @Test
    void register() {
    }

    @Test
    void login() {

    }

    @Test
    void selectById() {
    }

    @Test
    void updateUserAddress() {
    }

    @Test
    void updatePwd() {
    }

    @Test
    void getUserPage() {
    }

    @Test
    void delete() {
    }
}