package com.example.bookshop.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@SuppressWarnings("unused")
@Slf4j
@Data
public class Type {
    private int id;
    private String name;
    private String encodeName;

    public void setEncodeName(String encodeName) {
        this.encodeName = encodeName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
        this.encodeName = URLEncoder.encode(name, StandardCharsets.UTF_8);
    }

    public Type(int id) {
        super();
        this.id = id;
    }

    public Type(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public Type() {
        super();
    }

    public Type(String name) {
        super();
        this.name = name;
    }

}