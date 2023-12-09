package com.example.bookshop.service;

import com.example.bookshop.dao.GoodsDao;
import com.example.bookshop.model.Goods;
import com.example.bookshop.model.Page;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @className: GoodsService
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
 * @version: 1.0
 */
@Slf4j
@SuppressWarnings("unchecked,rawtypes")
public class GoodsService {
    private final GoodsDao gDao = new GoodsDao();

    public List<Map<String, Object>> getGoodsList(int recommendType) {
        List<Map<String, Object>> list = null;
        try {
            list = gDao.getGoodsList(recommendType);
        } catch (SQLException e) {
            log.info("SQLException", e);
        }
        return list;
    }

    public List<Map<String, Object>> getScrollGood() {
        List<Map<String, Object>> list = null;
        try {
            list = gDao.getScrollGood();
        } catch (SQLException e) {
            log.info("SQLException", e);
        }
        return list;
    }

    public List<Goods> selectGoodsByTypeID(int typeID, int pageNumber, int pageSize) {
        List<Goods> list = null;
        try {
            list = gDao.selectGoodsByTypeID(typeID, pageNumber, pageSize);
        } catch (SQLException e) {
            log.info("SQLException", e);
        }
        return list;
    }

    public Page selectPageByTypeID(int typeID, int pageNumber) {
        Page p = new Page();
        p.setPageNumber(pageNumber);
        int totalCount = 0;
        try {
            totalCount = gDao.getCountOfGoodsByTypeID(typeID);    //获取该类商品总数
        } catch (SQLException e) {
            log.info("SQLException", e);

        }
        p.SetPageSizeAndTotalCount(8, totalCount);      //设置页大小及该类商品总数

        List list = null;
        try {
            list = gDao.selectGoodsByTypeID(typeID, pageNumber, 8);    //根据商品类型id查找得商品List
        } catch (SQLException e) {
            log.info("SQLException", e);
        }

        p.setList(list);   //存入Page.List<Object>
        return p;
    }

    public Page getGoodsRecommendPage(int type, int pageNumber) {
        Page p = new Page();
        p.setPageNumber(pageNumber);
        int totalCount = 0;
        try {
            totalCount = gDao.getRecommendCountOfGoodsByTypeID(type);
        } catch (SQLException e) {
            log.info("SQLException", e);
        }
        p.SetPageSizeAndTotalCount(8, totalCount);
        List list = null;
        try {
            list = gDao.selectGoodsbyRecommend(type, pageNumber, 8);   //推荐类型所有商品详情List
            for (Goods g : (List<Goods>) list) {
                g.setScroll(gDao.isScroll(g));
                g.setHot(gDao.isHot(g));
                g.setNew(gDao.isNew(g));
            }
        } catch (SQLException e) {
            log.info("SQLException", e);
        }
        p.setList(list);
        return p;
    }

    public Goods getGoodsById(int id) {
        Goods g = null;
        try {
            g = gDao.getGoodsById(id);
        } catch (SQLException e) {
            log.info("SQLException", e);
        }
        return g;
    }

    public Page getSearchGoodsPage(String keyword, int pageNumber) {
        Page p = new Page();
        p.setPageNumber(pageNumber);
        int totalCount = 0;
        try {
//			totalCount = gDao.getGoodsCount(typeId);
            totalCount = gDao.getSearchCount(keyword);
        } catch (SQLException e) {
            log.info("SQLException", e);
        }
        p.SetPageSizeAndTotalCount(8, totalCount);
        List list = null;
        try {
//			list = gDao.selectGoods(keyword, pageNo, 8);
            list = gDao.selectSearchGoods(keyword, pageNumber, 8);
        } catch (SQLException e) {
            log.info("SQLException", e);
        }
        p.setList(list);
        return p;
    }

    public void addRecommend(int id, int type) {
        try {
            gDao.addRecommend(id, type);
        } catch (SQLException e) {
            log.info("SQLException", e);
        }
    }

    public void removeRecommend(int id, int type) {
        try {
            gDao.removeRecommend(id, type);
        } catch (SQLException e) {
            log.info("SQLException", e);
        }
    }

    public void insert(Goods goods) {
        try {
            gDao.insert(goods);
        } catch (SQLException e) {
            log.info("SQLException", e);
        }
    }

    public void update(Goods goods) {
        try {
            gDao.update(goods);
        } catch (SQLException e) {
            log.info("SQLException", e);

        }
    }

    public void delete(int id) {
        try {
            gDao.delete(id);
        } catch (SQLException e) {
            log.info("SQLException", e);

        }
    }
}
