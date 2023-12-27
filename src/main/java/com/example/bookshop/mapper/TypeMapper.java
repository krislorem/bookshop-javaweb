package com.example.bookshop.mapper;

import com.example.bookshop.model.Type;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TypeMapper {
    @Select("select * from type")
    List<Type> GetAllType();

    @Select("select * from type where id=#{typeid}")
    Type selectTypeNameByID(@Param("typeid") int typeid);


    @Select("select * from type where id = #{id}")
    Type select(@Param("id") int id);


    @Insert("insert into type(name) values(#{t.name})")
    Object insert(@Param("t") Type t);


    @Update("update type set name=#{t.name} where id =#{t.id}")
    Object update(@Param("t") Type t);

    @Delete("delete from `type` where id = #{id}")
    boolean delete(@Param("id") int id);
}
