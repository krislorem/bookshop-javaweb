package com.example.bookshop.service;

import com.example.bookshop.dao.TypeDao;
import com.example.bookshop.model.Type;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.List;

@Slf4j
public class TypeService {
    TypeDao tDao = new TypeDao();

    public List<Type> GetAllType() {
        List<Type> list = null;
        try {
            list = tDao.GetAllType();
        } catch (SQLException e) {
            log.info("SQLException", e);
        }
        return list;
    }

    public Type selectTypeNameByID(Integer typeid) {
        Type type = null;
        try {
            type = tDao.selectTypeNameByID(typeid);
        } catch (SQLException e) {
            log.info("SQLException", e);
        }
        return type;
    }

    public Type select(Integer id) {
        Type t = null;
        try {
            t = tDao.select(id);
        } catch (SQLException e) {
            log.info("SQLException", e);
        }
        return t;
    }

    public void insert(Type t) {
        try {
            tDao.insert(t);
        } catch (SQLException e) {
            log.info("SQLException", e);
        }
    }

    public void update(Type t) {
        try {
            tDao.update(t);
        } catch (SQLException e) {
            log.info("SQLException", e);
        }
    }

    public boolean delete(Integer id) {
        try {
            tDao.delete(id);
            return true;
        } catch (SQLException e) {
            log.info("SQLException", e);
            return false;
        }
    }
}
