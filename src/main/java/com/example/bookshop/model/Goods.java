package com.example.bookshop.model;

import lombok.Data;

/**
 * @className: Goods
 * @author: ZhaiJinPei
 * @discription: 商品实体
 */
@Data
@SuppressWarnings("unused")
public class Goods {

    private int id;
    private String name;    //书名
    private String cover;   //封面图
    private String image1;
    private String image2;

    private float price;    //价格
    private String author;  //作者
    private String press;   //出版社
    private String isbn;    //ISBN
    private String intro;   //详情

    private int stock;      //库存
    private Type type;

    private boolean isScroll;    //是否属条幅(首页轮播)
    private boolean isHot;       //是否属热销
    private boolean isNew;       //是否属新品

    public boolean getIsScroll() {
        return isScroll;
    }

    public void setScroll(boolean isScroll) {
        this.isScroll = isScroll;
    }

    public boolean getIsHot() {
        return isHot;
    }

    public void setHot(boolean isHot) {
        this.isHot = isHot;
    }

    public boolean getIsNew() {
        return isNew;
    }

    public void setNew(boolean isNew) {
        this.isNew = isNew;
    }

    public void setTypeid(int typeid) {
        if (type == null) {
            type = new Type();
        }
        type.setId(typeid);
    }

    public void setTypename(String typename) {
        if (type == null) {
            type = new Type();
        }
        type.setName(typename);
    }

    @Override
    public String toString() {
        return "Goods [id=" + id + ", name=" + name + ", cover=" + cover + ", image1=" + image1 + ", image2=" + image2
                + ", price=" + price + ", author=" + author + ", press=" + press + ", ISBN=" + isbn + ", intro=" + intro
                + ", stock=" + stock + ", type=" + type + "]";
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setType(Type type) {
        this.type = type;
    }


}
