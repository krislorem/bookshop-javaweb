# Bookshop
![l](https://img.shields.io/badge/组长-翟金培-purple)
![l](https://img.shields.io/badge/组员-仇星-blue)
![l](https://img.shields.io/badge/组员-李方圆-blue)
![l](https://img.shields.io/badge/组员-陈也-blue)
![l](https://img.shields.io/badge/组员-殷文喧-blue)
![l](https://img.shields.io/badge/组员-蔡吴江-blue)


​	    	本项目是一个实现网上图书商城的项目，分前台和后台管理系统，属于B/S交互系统。

------

#### &#10024;展示



- 前台首页:  https://pic.imgdb.cn/item/639c03ecb1fccdcd36ff92d5.png
  ![前台首页](https://files.superbed.cn/static/images/92/d5/639c03ecb1fccdcd36ff92d5.png)



- 前台首页热销推荐: https://pic.imgdb.cn/item/639c03f4b1fccdcd36ff9b11.png
  ![热销推荐](https://files.superbed.cn/static/images/9b/11/639c03f4b1fccdcd36ff9b11.png)



- 前台搜索作者结果: https://pic.imgdb.cn/item/639c03fdb1fccdcd36ffa249.png
  ![搜索作者结果](https://files.superbed.cn/static/images/a2/49/639c03fdb1fccdcd36ffa249.png)



- 前台书籍详情: https://pic.imgdb.cn/item/639c0404b1fccdcd36ffa989.png
  ![书籍详情](https://files.superbed.cn/static/images/a9/89/639c0404b1fccdcd36ffa989.png)



- 前台购物车: https://pic.imgdb.cn/item/639c040ab1fccdcd36ffb214.png
  ![前台购物车](https://files.superbed.cn/static/images/b2/14/639c040ab1fccdcd36ffb214.png)



- 后台首页: https://pic.imgdb.cn/item/639c0412b1fccdcd36ffb9ae.png
  ![后台首页](https://files.superbed.cn/static/images/b9/ae/639c0412b1fccdcd36ffb9ae.png)



- 后台客户管理: https://pic.imgdb.cn/item/639c041eb1fccdcd36ffc6ba.png
  ![客户管理](https://files.superbed.cn/static/images/c6/ba/639c041eb1fccdcd36ffc6ba.png)



- 后台订单管理: https://pic.imgdb.cn/item/639c0422b1fccdcd36ffc9ac.png
  ![订单管理](https://files.superbed.cn/static/images/c9/ac/639c0422b1fccdcd36ffc9ac.png)



- 后台商品管理_商品修改: https://pic.imgdb.cn/item/639c0426b1fccdcd36ffced0.png
  ![商品修改](https://files.superbed.cn/static/images/ce/d0/639c0426b1fccdcd36ffced0.png)



#### &#128640;大纲

- 前台
    - 用户注册功能
    - 用户登录功能
    - 购物车模块
    - 商品分类查询功能
    - 商品搜索功能
    - JS表单验证
- 后台
    - 商品管理模块
        - 添加
            - 图片上传
        - 修改
        - 删除
        - 加入or移出条幅推荐
        - 加入or移出热销推荐
        - 加入or移出新品推荐
    - 客户管理模块
        - 添加客户
        - 重置密码
        - 修改
        - 删除
    - 订单管理模块
        - 全部订单页
        - 未付款页
        - 已付款页
        - 配送中页
        - 已完成页
    - 商品类目管理模块
        - 增、删、改



*初始页为前台首页。*



#### &#127752;软件&环境

|    软件or环境     |    版本    |                    安装路径                     |
|:-------------:|:--------:|:-------------------------------------------:|
| IntelliJ IDEA | 2023.2.1 |  D:\ideaProfession\IntelliJ IDEA 2023.2.1   |
|    Tomcat     |  9.0.83  |        D:\tools\apache-tomcat-9.0.83        |
|     JDK17     |  17.0.9  |               F:\jdk-17.0.4.1               |
| Windows11专业版  |   22H3   |                     C盘                      |
|     MySQL     |  8.0.32  | C:\Program Files\MySQL\MySQL Server 8.0\bin |

仅供参考


#### &#127827;安装教程

1.  直接git clone本项目，在IDEA中打开.
1.  新建名为`bookshop`的数据库，然后执行项目resource里的`bookshop_localhost-dump.sql`中的sql语句建表.
1.  配置Tomcat9
1.  配置maven依赖
1.  修改`druid.properties`数据库连接池配置文件
1.  修改`mybatis-config.xml`mybatis配置文件
1.  运行Tomcat9


#### &#129;小组成员

- 2205221139 (组长)
- 2205221103
- 2205221122
- 2205221123
- 2205221133
- 2205221140
