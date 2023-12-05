package com.example.bookshop.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

@Slf4j
class PriceUtilsTest {
    @BeforeAll
    static void setUp() {
        log.info("PriceUtilsTest set up");
    }

    @AfterAll
    static void tearDown() {
        log.info("PriceUtilsTest tear down");
    }

    @Test
    void add() {
        log.info("PriceUtils.add(2.02544f,666.2014444f)");
        log.info(Float.toString(PriceUtils.add(2.02544f, 666.2014444f)));
    }

    @Test
    void testAdd() {
        log.info("PriceUtils.add(2.02544d,666.2014444d)");
        log.info(Double.toString(PriceUtils.add(2.02544d, 666.2014444d)));
    }

    @Test
    void subtract() {
        log.info("PriceUtils.subtract(2.02544f,666.2014444f)");
        log.info(Float.toString(PriceUtils.subtract(2.02544f, 666.2014444f)));
    }

    @Test
    void testSubtract() {
        log.info("PriceUtils.subtract(2.02544d,666.2014444d)");
        log.info(Double.toString(PriceUtils.subtract(2.02544d, 666.2014444d)));
    }
}