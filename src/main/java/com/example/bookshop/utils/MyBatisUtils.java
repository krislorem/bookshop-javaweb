package com.example.bookshop.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.function.Function;

public class MyBatisUtils {
    private static final SqlSessionFactory sqlSessionFactory;

    static {
        Reader reader;
        try {
            reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 执行查询操作
     *
     * @param func 需要执行查询的代码块
     * @return 查询结果
     */
    public static Object executeQuery(Function<SqlSession, Object> func) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Object res = func.apply(sqlSession);
        sqlSession.close();
        return res;
    }

    /**
     * 执行增、删、改操作
     *
     * @param func 需要执行的语句代码块
     */
    public static Object executeUpdate(Function<SqlSession, Object> func) {
        SqlSession sqlSession = sqlSessionFactory.openSession(false);
        // 手动提交/回滚事务
        try {
            Object obj = func.apply(sqlSession);
            // 手动提交事务
            sqlSession.commit();
            return obj;
        } catch (Exception e) {
            // 回滚事务
            sqlSession.rollback();
            throw e;
        } finally {
            // 关闭
            sqlSession.close();
        }
    }
}
