package com.example.bookshop.service;

import com.example.bookshop.mapper.TypeMapper;
import com.example.bookshop.model.Type;
import com.example.bookshop.utils.MyBatisUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@SuppressWarnings("unchecked")
public class TypeService {
    public List<Type> GetAllType() {
        return (List<Type>) MyBatisUtils.executeQuery(sqlSession -> sqlSession.getMapper(TypeMapper.class).GetAllType());
    }

    public Type selectTypeNameByID(Integer typeid) {
        return (Type) MyBatisUtils.executeQuery(sqlSession -> sqlSession.getMapper(TypeMapper.class).selectTypeNameByID(typeid));
    }

    public Type select(Integer id) {
        return (Type) MyBatisUtils.executeQuery(sqlSession -> sqlSession.getMapper(TypeMapper.class).select(id));
    }

    public void insert(Type t) {
        MyBatisUtils.executeUpdate(sqlSession -> sqlSession.getMapper(TypeMapper.class).insert(t));
    }

    public void update(Type t) {
        MyBatisUtils.executeUpdate(sqlSession -> sqlSession.getMapper(TypeMapper.class).update(t));
    }

    public boolean delete(Integer id) {
        return (boolean) MyBatisUtils.executeQuery(sqlSession -> sqlSession.getMapper(TypeMapper.class).delete(id));
    }
}
