package com.example.bookshop.model;

import lombok.Data;
import lombok.Setter;

@Setter
@Data
@SuppressWarnings("unused")
public class User {
    private int id;
    private String username;
    private String email;
    private String password;
    private String name;
    private String phone;     //电话
    private String address;    //收货地址

    private boolean isadmin = false;   //是否为管理员

    private boolean isvalidate = false;   //账户是否有效

    public User(int id, String username, String email, String password, String name, String phone, String address,
                boolean isadmin, boolean isvalidate) {
        super();
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.isadmin = isadmin;
        this.isvalidate = isvalidate;
    }

    public User(String username, String email, String password, String name, String phone, String address) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.isadmin = false;
        this.isvalidate = false;
    }

    public User() {
        super();
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + ", name="
               + name + ", phone=" + phone + ", address=" + address + ", isadmin=" + isadmin + ", isvalidate="
               + isvalidate + "]";
    }
}
