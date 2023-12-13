package com.example.bookshop.mapper;

import com.example.bookshop.model.Goods;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface GoodsMapper {
    @Select("select g.id,g.name,g.cover,g.price,t.name typename from recommend r,goods g,type t where type=#{recommendType} and r.goods_id=g.id and g.type_id=t.id")
    List<Map<String, Object>> getGoodsList(@Param("recommendType") int recommendType);

    @Select("select g.id,g.name,g.cover,g.price  from recommend r,goods g where r.goods_id=g.id")
    List<Map<String, Object>> getScrollGood();

    @Select("""
            <script>
                select * from goods
                <if test = "typeID != 0">
                    where type_id = #{typeID}
                </if>
                limit #{pageNumber} , #{pageSize}
            </script>
            """)
    List<Goods> selectGoodsByTypeID(@Param("typeID") int typeID, @Param("pageNumber") int pageNumber, @Param("pageSize") int pageSize);

    @Select("""
            <script>
               select count(*) from goods
               <if test = "typeID != 0">
                   where type_id=#{typeID}
               </if>
            </script>
            """)
    int getCountOfGoodsByTypeID(@Param("typeID") int typeID);

    @Select("""
            <script>
                <choose>
                    <when test="type == 0">
                        select g.id,g.name,g.cover,g.image1,g.image2,g.author,g.press,g.isbn,g.intro,g.price,g.stock,t.name typename
                        from goods g,type t
                        where g.type_id=t.id
                        order by g.id
                        limit #{pageNumber},#{pageSize}
                    </when>
                    <otherwise>
                        select g.id,g.name,g.cover,g.image1,g.image2,g.author,g.press,g.isbn,g.intro,g.price,g.stock,t.name typename
                        from goods g,recommend r,type t
                        where g.id=r.goods_id and g.type_id=t.id and r.type = #{type}
                        order by g.id
                        limit #{pageNumber},#{pageSize}
                    </otherwise>
                </choose>
            </script>
            """)
    List<Goods> selectGoodsByRecommend(@Param("type") int type, @Param("pageNumber") int pageNumber, @Param("pageSize") int pageSize);

    @Select("""
            <script>
                <choose>
                    <when test="type == 0">
                            select count(*) from goods
                    </when>
                    <otherwise>
                        select count(*) from recommend where type=#{type}
                    </otherwise>
                </choose>
            </script>
            """)
    int getRecommendCountOfGoodsByTypeID(@Param("type") int type);

    @Select("select g.id,g.name,g.cover,g.image1,g.image2,g.author,g.press,g.isbn,g.price,g.intro,g.stock,t.id typeid,t.name typename from goods g,type t where g.id = #{id} and g.type_id=t.id")
    Goods getGoodsById(@Param("id") int id);

    @Select("select count(*) from goods where name like '%${keyword}%'")
    int getSearchCount(@Param("keyword") String keyword);

    @Select("select * from goods where concat(name,author) like '%${keyword}%' limit #{pageNumber},#{pageSize}")
    List<Goods> selectSearchGoods(@Param("keyword") String keyword, @Param("pageNumber") int pageNumber, @Param("pageSize") int pageSize);

    @Select("select exists(select * from recommend where type=1 and goods_id=#{g.id})")
    boolean isScroll(@Param("g") Goods g);

    @Select("select exists(select * from recommend where type=2 and goods_id=#{g.id})")
    boolean isHot(@Param("g") Goods g);

    @Select("select exists(select * from recommend where type=3 and goods_id=#{g.id})")
    boolean isNew(@Param("g") Goods g);

    @Insert("insert into recommend(type,goods_id) values(#{type},#{id})")
    int addRecommend(@Param("id") int id, @Param("type") int type);

    @Delete("delete from recommend where type=#{type} and goods_id=#{id}")
    int removeRecommend(@Param("id") int id, @Param("type") int type);

    @Insert("insert into goods(name,cover,image1,image2,price,author,press,isbn,intro,stock,type_id) values(#{g.name},#{g.cover},#{g.image1},#{g.image2},#{g.price},#{g.author},#{g.press},#{g.isbn},#{g.intro},#{g.stock},#{g.type.id})")
    int insert(@Param("g") Goods g);

    @Update("update goods set name=#{g.name},cover=#{g.cover},image1=#{g.image1},image2=#{g.image2},price=#{g.price},author=#{g.author},press=#{g.press},isbn=#{g.isbn},intro=#{g.intro},stock=#{g.stock},type_id=#{g.type.id} where id=#{g.id}")
    int update(@Param("g") Goods g);

    @Delete("delete from goods where id = #{id}")
    int delete(@Param("id") int id);
}
