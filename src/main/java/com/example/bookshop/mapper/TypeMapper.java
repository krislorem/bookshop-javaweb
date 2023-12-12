package com.example.bookshop.mapper;

import com.example.bookshop.model.Type;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TypeMapper {
    @Select("select * from type")
    List<Type> GetAllType();

    @Select("select * from type where id=#{typeid}")
    Type selectTypeNameByID(@Param("typeid") int typeid);


    @Select("select * from type where id = #{id}")
    Type select(@Param("id") int id);


    @Select("insert into type(name) values(#{t.name})")
    Object insert(@Param("t") Type t);


    @Select("update type set name=#{t.name} where id =#{t.id}")
    Object update(@Param("t") Type t);

    @Select("delete from type where id = #{id}")
    Object delete(@Param("id") int id);
}
