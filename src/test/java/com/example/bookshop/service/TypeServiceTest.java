package com.example.bookshop.service;

import com.example.bookshop.mapper.TypeMapper;
import com.example.bookshop.utils.MyBatisUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

@Slf4j
class TypeServiceTest {
    private final TypeService service = new TypeService();
    @BeforeAll
    static void setUp() {
        log.info("TypeServiceTest set up");
    }

    @AfterAll
    static void tearDown() {
        log.info("TypeServiceTest tear down");
    }

    @Test
    void getAllType() {
        service.GetAllType().forEach(System.out::println);
    }

    @Test
    void selectTypeNameByID() {
    }

    @Test
    void select() {
        System.out.println(service.select(1));
    }

    @Test
    void insert() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
        log.info(String.valueOf((boolean) MyBatisUtils.executeUpdate(sqlSession -> sqlSession.getMapper(TypeMapper.class).delete(19))));
    }
}