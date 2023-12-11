package com.example.bookshop.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @className: DataSourceUtils
 * @author: ZhaiJinPei
 * @discription: 获取数据源和数据库连接
 * @version: 1.0
 */
public class DataSourceUtils {
    //    private static final DataSource ds = new ComboPooledDataSource();
//    private static final DataSource ds = new DruidDataSource();
    private static final DataSource ds;
    static {
        try {
            Properties properties = new Properties();
            properties.load(DataSourceUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public DataSourceUtils() throws Exception {
    }
    public static void close(Statement statement,Connection connection){
        close(null, statement, connection);
    }
    public static void close(ResultSet resultSet,Statement statement, Connection connection){
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static DataSource getDataSource() throws Exception {
        return ds;
    }

    public static Connection getConnection() throws Exception {
        return ds.getConnection();
    }
}
