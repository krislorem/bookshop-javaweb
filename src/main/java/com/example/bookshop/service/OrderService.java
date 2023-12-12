package com.example.bookshop.service;

import com.example.bookshop.mapper.OrderMapper;
import com.example.bookshop.model.Order;
import com.example.bookshop.model.OrderItem;
import com.example.bookshop.model.Page;
import com.example.bookshop.utils.MyBatisUtils;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.List;

@SuppressWarnings("rawtypes,unchecked")
@Slf4j
public class OrderService {
//    private final OrderDao oDao = new OrderDao();

    public void addOrder(Order order) throws SQLException {
//        Connection  con = new DruidDataSourceFactory().getDataSource().getConnection();;
//        try {
//            con = DataSourceUtils.getConnection();

//            con.setAutoCommit(false);

        MyBatisUtils.executeUpdate(sqlSession -> sqlSession.getMapper(OrderMapper.class).insertOrder(order));
        int id = Integer.parseInt(String.valueOf(MyBatisUtils.executeQuery(sqlSession -> sqlSession.getMapper(OrderMapper.class).getLastInsertId())));
        order.setId(id);
        for (OrderItem item : order.getItemMap().values()) {
            MyBatisUtils.executeUpdate(sqlSession -> sqlSession.getMapper(OrderMapper.class).insertOrderItem(item));
        }
//            con.commit();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
    }

    public List<Order> selectAll(int userid) {
//        List<Order> list;
//        try {
        List<Order> list = (List<Order>) MyBatisUtils.executeQuery(sqlSession -> sqlSession.getMapper(OrderMapper.class).selectAll(userid));
        for (Order o : list) {
            List<OrderItem> l = (List<OrderItem>) MyBatisUtils.executeQuery(sqlSession -> sqlSession.getMapper(OrderMapper.class).selectAllItem(o.getId()));
            o.setItemList(l);
        }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
        return list;
    }

    public Page getOrderPage(int status, int pageNumber) {
        Page p = new Page();
        p.setPageNumber(pageNumber);
        int pageSize = 10;
//        int totalCount;
//        try {
        int totalCount = (int) MyBatisUtils.executeQuery(sqlSession -> sqlSession.getMapper(OrderMapper.class).getOrderCount(status));
//        } catch (Exception e) {
//            log.info("SQLException", e);
//
//        }
//        log.info("Total count: " + totalCount);
        p.SetPageSizeAndTotalCount(pageSize, totalCount);
//        List list = null;
//        try {
        List list = (List<Order>) MyBatisUtils.executeQuery(sqlSession -> sqlSession.getMapper(OrderMapper.class).selectOrderList(status, (pageNumber - 1) * pageSize, pageSize));
        for (Order o : (List<Order>) list) {
            List<OrderItem> l = (List<OrderItem>) MyBatisUtils.executeQuery(sqlSession -> sqlSession.getMapper(OrderMapper.class).selectAllItem(o.getId()));
            o.setItemList(l);
        }
//        } catch (Exception e) {
//            log.info("SQLException", e);
//
//        }
        p.setList(list);
        return p;
    }

    public void updateStatus(int id, int status) {
//        try {
        MyBatisUtils.executeUpdate(sqlSession -> sqlSession.getMapper(OrderMapper.class).updateStatus(id, status));
//        } catch (Exception e) {
//            log.info("SQLException", e);
//
//        }
    }

    public void delete(int id) {
//        Connection con = null;
//        try {
//            con = DataSourceUtils.getDataSource().getConnection();
//            con.setAutoCommit(false);

        MyBatisUtils.executeUpdate(sqlSession -> sqlSession.getMapper(OrderMapper.class).deleteOrderItem(id));
        MyBatisUtils.executeUpdate(sqlSession -> sqlSession.getMapper(OrderMapper.class).deleteOrder(id));
//            con.commit();
//        } catch (Exception e) {
//            log.info("SQLException", e);

//            if (con != null)
//                try {
//                    con.rollback();
//                } catch (SQLException e1) {
//                    log.info("SQLException", e);
//
//                }
//        }
    }
}
