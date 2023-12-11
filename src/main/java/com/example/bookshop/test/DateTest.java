package com.example.bookshop.test;

import java.time.LocalDateTime;

public class DateTest {
    public static String localDateTime() {
        System.out.println("LocalDateTime.now() = " + LocalDateTime.now());
        String s = LocalDateTime.now().toString();
        return "替换掉T = " + s.replace("T", " ");
    }
}
