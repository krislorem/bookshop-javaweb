package com.example.bookshop.mapper;

import com.example.bookshop.model.Order;
import com.example.bookshop.model.OrderItem;
import org.apache.ibatis.annotations.*;

import java.math.BigInteger;
import java.util.List;

public interface OrderMapper {
    @Insert("insert into `order`(total,amount,status,paytype,name,phone,address,datetime,user_id) values(#{o.total},#{o.amount},#{o.status},#{o.paytype},#{o.name},#{o.phone},#{o.address},#{o.datetime},#{o.user.id})")
    int insertOrder(@Param("o") Order o);

    @Select("select last_insert_id()")
    BigInteger getLastInsertId();

    @Insert("insert into orderitem(price,amount,goods_id,order_id) values(#{i.price},#{i.amount},#{i.goods.id},#{i.order.id})")
    int insertOrderItem(@Param("i") OrderItem i);

    @Select("select * from `order` where user_id=#{userid} order by datetime desc")
    List<Order> selectAll(@Param("userid") int userid);

    @Select("select i.id,i.price,i.amount,g.name from orderitem i,goods g where order_id=#{orderid} and i.goods_id=g.id")
    List<OrderItem> selectAllItem(@Param("orderid") int orderid);

    @Select("""
            <script>
                <choose>
                    <when test = "status == 0">
                        select count(*) from `order`
                    </when>
                    <otherwise>
                        select count(*) from `order` where status=#{status}
                    </otherwise>
                </choose>
            </script>
            """)
    int getOrderCount(@Param("status") int status);

    @Select("""
            <script>
                <choose>
                    <when test = "status == 0">
                        select o.id,o.total,o.amount,o.status,o.paytype,o.name,o.phone,o.address,o.datetime,u.username from `order` o,user u where o.user_id=u.id order by o.datetime desc limit #{pageNumber},#{pageSize}
                    </when>
                    <otherwise>
                        select o.id,o.total,o.amount,o.status,o.paytype,o.name,o.phone,o.address,o.datetime,u.username from `order` o,user u where o.user_id=u.id and o.status=#{status} order by o.datetime desc limit #{pageNumber},#{pageSize}
                    </otherwise>
                </choose>
            </script>
            """)
    List<Order> selectOrderList(@Param("status") int status, @Param("pageNumber") int pageNumber, @Param("pageSize") int pageSize);

    @Update("update `order` set status=#{status} where id = #{id}")
    int updateStatus(@Param("id") int id, @Param("status") int status);

    @Delete("delete from order where id=#{id}")
    int deleteOrder(@Param("id") int id);

    @Delete("delete from orderitem where order_id=#{id}")
    int deleteOrderItem(@Param("id") int id);
}
