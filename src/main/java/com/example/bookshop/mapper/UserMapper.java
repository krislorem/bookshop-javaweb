package com.example.bookshop.mapper;

import com.example.bookshop.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    @Select("insert into user(username,email,password,name,phone,address,isadmin,isvalidate) values(#{u.username},#{u.email},#{u.password},#{u.name},#{u.phone},#{u.address},#{u.isadmin},#{u.isvalidate})")
    Object addUser(@Param("u") User u);

    @Select("select exists(select * from user where username = #{username})")
    boolean isUsernameExist(@Param("username") String username);

    @Select("select exists(select * from user where email = #{email})")
    boolean isEmailExist(@Param("email") String email);

    @Select("select * from user where username=#{username} and password=#{password}")
    User selectByUsernamePassword(@Param("username") String username, @Param("password") String password);

    @Select("select * from user where email=#{email} and password=#{password}")
    User selectByEmailPassword(@Param("email") String email, @Param("password") String password);

    @Select("select * from user where id=#{id}")
    User selectById(@Param("id") int id);

    @Select("update user set name = #{user.name},phone=#{user.phone},address=#{user.address} where id = #{user.id}")
    Object updateUserAddress(@Param("user") User user);

    @Select("update user set password = #{user.password} where id = #{user.id}")
    Object updatePwd(@Param("user") User user);

    @Select("select count(*) from user")
    int selectUserCount();

    @Select("select * from user limit #{pageNo},#{pageSize}")
    List<User> selectUserList(@Param("pageNo") int pageNo, @Param("pageSize") int pageSize);

    @Select("delete from user where id = #{id}")
    Object delete(@Param("id") int id);
}
