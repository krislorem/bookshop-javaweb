package com.example.bookshop.service;

import com.example.bookshop.mapper.GoodsMapper;
import com.example.bookshop.model.Goods;
import com.example.bookshop.model.Page;
import com.example.bookshop.utils.MyBatisUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * @discription: 处理商品的业务逻辑, 调用 GoodsDao层方法对数据库进行操作
 * 1.getGoodsList(推荐类型)                      获取该类型商品List
 * 2.getScrollGood()                            获取推荐栏(轮播)商品List
 * 3.selectGoodsByTypeID(书籍类型id,页码,页容量)   根据商品类型查找商品(List)
 * 4.selectPageByTypeID(书籍类型id,页码)          查询该类型的页信息
 * 5.getGoodsRecommendPage(书籍类型,页码)         获得推荐类型所有商品详情的页信息
 * 6.getGoodsById(商品id)                        获得商品信息
 * 7.getSearchGoodsPage(keyword,页码)            根据keyword字段模糊搜索
 * 8.addRecommend(商品id,商品类型)                将该商品插入(推荐、热销、新品)类型
 * 9.removeRecommend(商品id,商品类型)             删除该商品的类型(推荐、热销、新品)
 * 10.insert(Goods goods)                       新增商品
 * 11.update(Goods goods)                       修改商品
 * 12.delete(商品id)                             删除商品
 */
@Slf4j
@SuppressWarnings("unchecked,rawtypes")
public class GoodsService {
    public List<Map<String, Object>> getGoodsList(int recommendType) {
        return (List<Map<String, Object>>) MyBatisUtils.executeQuery(sqlSession -> sqlSession.getMapper(GoodsMapper.class).getGoodsList(recommendType));
    }

    public List<Map<String, Object>> getScrollGood() {
        return (List<Map<String, Object>>) MyBatisUtils.executeQuery(sqlSession -> sqlSession.getMapper(GoodsMapper.class).getScrollGood());
    }

    public List<Goods> selectGoodsByTypeID(int typeID, int pageNumber, int pageSize) {
        return (List<Goods>) MyBatisUtils.executeQuery(sqlSession -> sqlSession.getMapper(GoodsMapper.class).selectGoodsByTypeID(typeID, pageNumber, pageSize));
    }

    public Page selectPageByTypeID(int typeID, int pageNumber) {
        Page p = new Page();
        p.setPageNumber(pageNumber);
        int totalCount = (int) MyBatisUtils.executeQuery(sqlSession -> sqlSession.getMapper(GoodsMapper.class).getCountOfGoodsByTypeID(typeID));    //获取该类商品总数
        p.SetPageSizeAndTotalCount(8, totalCount);      //设置页大小及该类商品总数
        List list = (List<Goods>) MyBatisUtils.executeQuery(sqlSession -> sqlSession.getMapper(GoodsMapper.class).selectGoodsByTypeID(typeID, pageNumber, 8));    //根据商品类型id查找得商品List
        p.setList(list);   //存入Page.List<Object>
        return p;
    }

    public Page getGoodsRecommendPage(int type, int pageNumber) {
        Page p = new Page();
        p.setPageNumber(pageNumber);
        int totalCount = (int) MyBatisUtils.executeQuery(sqlSession -> sqlSession.getMapper(GoodsMapper.class).getRecommendCountOfGoodsByTypeID(type));
        p.SetPageSizeAndTotalCount(8, totalCount);
        List list = (List<Goods>) MyBatisUtils.executeQuery(sqlSession -> sqlSession.getMapper(GoodsMapper.class).selectGoodsByRecommend(type, (pageNumber - 1) * 8, 8));   //推荐类型所有商品详情List
        for (Goods g : (List<Goods>) list) {
            g.setScroll((boolean) MyBatisUtils.executeQuery(sqlSession -> sqlSession.getMapper(GoodsMapper.class).isScroll(g)));
            g.setHot((boolean) MyBatisUtils.executeQuery(sqlSession -> sqlSession.getMapper(GoodsMapper.class).isHot(g)));
            g.setNew((boolean) MyBatisUtils.executeQuery(sqlSession -> sqlSession.getMapper(GoodsMapper.class).isNew(g)));
        }
        p.setList(list);
        return p;
    }

    public Goods getGoodsById(int id) {
        return (Goods) MyBatisUtils.executeQuery(sqlSession -> sqlSession.getMapper(GoodsMapper.class).getGoodsById(id));
    }

    public Page getSearchGoodsPage(String keyword, int pageNumber) {
        Page p = new Page();
        p.setPageNumber(pageNumber);
        int totalCount = (int) MyBatisUtils.executeQuery(sqlSession -> sqlSession.getMapper(GoodsMapper.class).getSearchCount(keyword));
        p.SetPageSizeAndTotalCount(8, totalCount);
        List list = (List<Goods>) MyBatisUtils.executeQuery(sqlSession -> sqlSession.getMapper(GoodsMapper.class).selectSearchGoods(keyword, (pageNumber - 1) * 8, 8));
        p.setList(list);
        return p;
    }

    public void addRecommend(int id, int type) {
        MyBatisUtils.executeUpdate(sqlSession -> sqlSession.getMapper(GoodsMapper.class).addRecommend(id, type));
    }

    public void removeRecommend(int id, int type) {
        MyBatisUtils.executeUpdate(sqlSession -> sqlSession.getMapper(GoodsMapper.class).removeRecommend(id, type));
    }

    public void insert(Goods goods) {
        MyBatisUtils.executeUpdate(sqlSession -> sqlSession.getMapper(GoodsMapper.class).insert(goods));
    }

    public void update(Goods goods) {
        MyBatisUtils.executeUpdate(sqlSession -> sqlSession.getMapper(GoodsMapper.class).update(goods));
    }

    public void delete(int id) {
        MyBatisUtils.executeUpdate(sqlSession -> sqlSession.getMapper(GoodsMapper.class).delete(id));
    }
}
