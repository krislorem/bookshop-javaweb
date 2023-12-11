package com.example.bookshop.dao;

import com.example.bookshop.model.Type;
import com.example.bookshop.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class TypeDao {
    public List<Type> GetAllType() throws Exception {
        QueryRunner r = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from type";
        return r.query(sql, new BeanListHandler<>(Type.class));
    }

    public Type selectTypeNameByID(int typeid) throws Exception {
        QueryRunner r = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from type where id=?";
        return r.query(sql, new BeanHandler<>(Type.class), typeid);
    }

    public Type select(int id) throws Exception {
        QueryRunner r = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from type where id = ?";
        return r.query(sql, new BeanHandler<>(Type.class), id);
    }

    public void insert(Type t) throws Exception {
        QueryRunner r = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "insert into type(name) values(?)";
        r.update(sql, t.getName());
    }

    public void update(Type t) throws Exception {
        QueryRunner r = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "update type set name=? where id = ?";
        r.update(sql, t.getName(), t.getId());
    }

    public void delete(int id) throws Exception {
        QueryRunner r = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "delete from type where id = ?";
        r.update(sql, id);
    }
}
