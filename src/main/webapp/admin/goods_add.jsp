<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>商品添加</title>
    <meta charset="utf-8"/>
    <link rel="stylesheet" href="css/bootstrap.css"/>
    <script src="js/jquery.js"></script>
    <script src="js/jquery.validate.min.js"></script>
    <script src="js/messages_zh.min.js"></script>
    <script>
        $(document).ready(function () {
            // 项目一开始就要初始化验证
            $("#goodsadd").validate({
                errorElement: 'span',
                errorClass: 'error-block',
                onfocusout: function (element) {
                    $(element).valid();
                },
                rules: {
                    price: {
                        required: true,
                        price: true
                    }
                },
                messages: {
                    price: {
                        required: "请输入价格",
                    },
                }
            })

            // 自定义验证价格
            jQuery.validator.addMethod("price", function (value, element) {
                var price = /^([1-9][0-9]*|0)(\.[0-9]*[1-9])?$/;
                return this.optional(element) || (price.test(value));
            }, "请输入正确的价格");
        })

    </script>
</head>
<body>
<div class="container-fluid">

    <jsp:include page="/admin/header.jsp"/>

    <br><br>
    <form id="goodsadd" class="form-horizontal" action="${pageContext.request.contextPath }/admin/goods_add"
          method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="name" class="col-sm-1 control-label">名称</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" id="name" name="name" required="required">
            </div>
        </div>
        <div class="form-group">
            <label for="price" class="col-sm-1 control-label">价格</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" id="price" name="price" required="required">
            </div>
        </div>
        <div class="form-group">
            <label for="author" class="col-sm-1 control-label">作者</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" id="author" name="author">
            </div>
        </div>
        <div class="form-group">
            <label for="press" class="col-sm-1 control-label">出版社</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" id="press" name="press">
            </div>
        </div>
        <div class="form-group">
            <label for="isbn" class="col-sm-1 control-label">ISBN</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" id="isbn" name="isbn">
            </div>
        </div>
        <div class="form-group">
            <label for="intro" class="col-sm-1 control-label">介绍</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" id="intro" name="intro">
            </div>
        </div>
        <div class="form-group">
            <label for="stock" class="col-sm-1 control-label">库存</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" id="stock" name="stock">
            </div>
        </div>
        <div class="form-group">
            <label for="cover" class="col-sm-1 control-label">封面图片</label>
            <div class="col-sm-6">
                <input type="file" name="cover" id="cover" required="required">推荐尺寸: 500 * 500
            </div>
        </div>
        <div class="form-group">
            <label for="image1" class="col-sm-1 control-label">详情图片1</label>
            <div class="col-sm-6">
                <input type="file" name="image1" id="image1" required="required">推荐尺寸: 500 * 500
            </div>
        </div>
        <div class="form-group">
            <label for="image2" class="col-sm-1 control-label">详情图片2</label>
            <div class="col-sm-6">
                <input type="file" name="image2" id="image2" required="required">推荐尺寸: 500 * 500
            </div>
        </div>
        <div class="form-group">
            <label for="select_topic" class="col-sm-1 control-label">类目</label>
            <div class="col-sm-6">
                <select class="form-control" id="select_topic" name="typeid">

                    <c:forEach items="${typeList }" var="t">
                        <option value="${t.id }">${t.name }</option>
                    </c:forEach>


                </select>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-1 col-sm-10">
                <button type="submit" class="btn btn-success">提交保存</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>