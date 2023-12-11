package com.example.bookshop.service;

import com.example.bookshop.model.Goods;
import com.example.bookshop.model.Type;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

@Slf4j
class GoodsServiceTest {
    private final GoodsService service = new GoodsService();

    @BeforeAll
    static void setUp() {
        log.info("GoodsServiceTest set up");
    }

    @AfterAll
    static void tearDown() {
        log.info("GoodsServiceTest tear down");
    }

    /**
     * 1.getGoodsList(推荐类型)                      获取该类型商品List
     */
    @Test
    void getGoodsList() {
        log.info("GoodsServiceTest getGoodsList");
        service.getGoodsList(3).forEach(System.out::println);
    }

    /**
     * 2.getScrollGood()                            获取推荐栏(轮播)商品List
     */
    @Test
    void getScrollGood() {
        log.info("getScrollGood");
        service.getScrollGood().forEach(System.out::println);
    }

    /**
     * 3.selectGoodsByTypeID(书籍类型id,页码,页容量)   根据商品类型查找商品(List)
     */
    @Test
    void selectGoodsByTypeID() {
        log.info("selectGoodsByTypeID" );
        service.selectGoodsByTypeID(1, 1, 10).forEach(System.out::println);
    }

    /**
     * 4.selectPageByTypeID(书籍类型id,页码)          查询该类型的页信息
     */
    @Test
    void selectPageByTypeID() {
        log.info("selectPageByTypeID" );
        log.info(service.selectPageByTypeID(1, 1).toString());
    }

    /**
     * 5.getGoodsRecommendPage(书籍类型,页码)         获得推荐类型所有商品详情的页信息
     */
    @Test
    void getGoodsRecommendPage() {
        log.info("getGoodsRecommendPage");
        log.info(service.getGoodsRecommendPage(1, 1).toString());
    }

    /**
     * 6.getGoodsById(商品id)                        获得商品信息
     */
    @Test
    void getGoodsById() {
        log.info("getGoodsById");
        log.info(service.getGoodsById(2).toString());
    }

    /**
     * 7.getSearchGoodsPage(keyword,页码)            根据keyword字段模糊搜索
     */
    @Test
    void getSearchGoodsPage() {
        log.info("getSearchGoodsPage");
        log.info(service.getSearchGoodsPage("java核心技术", 1).toString());
    }

    /**
     * 8.addRecommend(商品id,商品类型)                将该商品插入(推荐、热销、新品)类型
     */
    @Test
    void addRecommend() {
        log.info("addRecommend");
        service.addRecommend(412,1);
    }

    /**
     * 9.removeRecommend(商品id,商品类型)             删除该商品的类型(推荐、热销、新品)
     */
    @Test
    void removeRecommend() {
        log.info("removeRecommend");
        service.removeRecommend(412,1);
    }

    /**
     * 10.insert(Goods goods)                       新增商品
     */
    @Test
    void insert() {
        log.info("insert");
        Goods goods = new Goods();
        goods.setName("Goods");
        goods.setCover("Goods");
        goods.setPrice(100);
        goods.setStock(10);
        goods.setImage1("s");
        goods.setImage2("s");
        goods.setType(new Type(1));
        service.insert(goods);
    }

    /**
     * 11.update(Goods goods)                       修改商品
     */
    @Test
    void update() {
        log.info("update");
        Goods goods = service.getGoodsById(412);
        goods.setImage1("sad");
        service.update(goods);
    }

    /**
     * 12.delete(商品id)                             删除商品
     */
    @Test
    void delete() {
        log.info("Delete");
        service.delete(412);
    }
}