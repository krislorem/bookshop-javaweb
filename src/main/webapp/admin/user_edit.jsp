<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>客户修改</title>
    <meta charset="utf-8"/>
    <link rel="stylesheet" href="css/bootstrap.css"/>
    <script src="js/jquery.js"></script>
    <script src="js/jquery.validate.min.js"></script>
    <script src="js/messages_zh.min.js"></script>
    <script>
        $(document).ready(function () {
            // 项目一开始就要初始化验证
            $("#useredit").validate({
                errorElement: 'span',
                errorClass: 'error-block',
                onfocusout: function (element) {
                    $(element).valid();
                },
                rules: {
                    name: {
                        required: true
                    },
                    phone: {
                        required: true,
                        phone: true
                    },
                    address: {
                        required: true
                    }
                },
                messages: {
                    name: {
                        required: "请输入用户名",
                    },
                    phone: {
                        required: "请输入电话",
                        phone: "请输入正确的电话号码"
                    },
                    address: {
                        required: "请输入地址"
                    }
                }
            })

            // 自定义验证电话
            jQuery.validator.addMethod("phone", function (value, element) {
                var phone = /^1[34578][0-9]{9}$/;
                return this.optional(element) || (phone.test(value));
            }, "请输入正确格式的邮箱");
        })

    </script>
</head>
<body>
<div class="container-fluid">

    <jsp:include page="/admin/header.jsp"/>

    <br><br>

    <form id="useredit" class="form-horizontal" action="${pageContext.request.contextPath }/admin/user_edit"
          method="post">
        <input type="hidden" name="id" value="${u.id }">
        <div class="form-group">
            <label for="username" class="col-sm-1 control-label">用户名</label>
            <div id="username" class="col-sm-5">${u.username }</div>
        </div>
        <div class="form-group">
            <label for="email" class="col-sm-1 control-label">邮箱</label>
            <div id="email" class="col-sm-5">${u.email }</div>
        </div>
        <div class="form-group">
            <label for="name" class="col-sm-1 control-label">收货人</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" id="name" name="name" value="${u.name }">
            </div>
        </div>
        <div class="form-group">
            <label for="phone" class="col-sm-1 control-label">电话</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" id="phone" name="phone" value="${u.phone }">
            </div>
        </div>
        <div class="form-group">
            <label for="address" class="col-sm-1 control-label">地址</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" id="address" name="address" value="${u.address }">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-1 col-sm-10">
                <button type="submit" class="btn btn-success">提交修改</button>
            </div>
        </div>
    </form>

    <span style="color:red;"></span>

</div>
</body>
</html>