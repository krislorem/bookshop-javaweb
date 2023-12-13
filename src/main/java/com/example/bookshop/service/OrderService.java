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
    public void addOrder(Order order) throws SQLException {
        MyBatisUtils.executeUpdate(sqlSession -> sqlSession.getMapper(OrderMapper.class).insertOrder(order));
        int id = Integer.parseInt(String.valueOf(MyBatisUtils.executeQuery(sqlSession -> sqlSession.getMapper(OrderMapper.class).getLastInsertId())));
        order.setId(id);
        for (OrderItem item : order.getItemMap().values()) {
            MyBatisUtils.executeUpdate(sqlSession -> sqlSession.getMapper(OrderMapper.class).insertOrderItem(item));
        }
    }

    public List<Order> selectAll(int userid) {
        List<Order> list = (List<Order>) MyBatisUtils.executeQuery(sqlSession -> sqlSession.getMapper(OrderMapper.class).selectAll(userid));
        for (Order o : list) {
            List<OrderItem> l = (List<OrderItem>) MyBatisUtils.executeQuery(sqlSession -> sqlSession.getMapper(OrderMapper.class).selectAllItem(o.getId()));
            o.setItemList(l);
        }
        return list;
    }

    public Page getOrderPage(int status, int pageNumber) {
        Page p = new Page();
        p.setPageNumber(pageNumber);
        int pageSize = 10;
        int totalCount = (int) MyBatisUtils.executeQuery(sqlSession -> sqlSession.getMapper(OrderMapper.class).getOrderCount(status));
        p.SetPageSizeAndTotalCount(pageSize, totalCount);
        List list = (List<Order>) MyBatisUtils.executeQuery(sqlSession -> sqlSession.getMapper(OrderMapper.class).selectOrderList(status, (pageNumber - 1) * pageSize, pageSize));
        for (Order o : (List<Order>) list) {
            List<OrderItem> l = (List<OrderItem>) MyBatisUtils.executeQuery(sqlSession -> sqlSession.getMapper(OrderMapper.class).selectAllItem(o.getId()));
            o.setItemList(l);
        }
        p.setList(list);
        return p;
    }

    public void updateStatus(int id, int status) {
        MyBatisUtils.executeUpdate(sqlSession -> sqlSession.getMapper(OrderMapper.class).updateStatus(id, status));
    }

    public void delete(int id) {
        MyBatisUtils.executeUpdate(sqlSession -> sqlSession.getMapper(OrderMapper.class).deleteOrderItem(id));
        MyBatisUtils.executeUpdate(sqlSession -> sqlSession.getMapper(OrderMapper.class).deleteOrder(id));
    }
}
