<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>客户添加</title>
    <meta charset="utf-8"/>
    <link rel="stylesheet" href="css/bootstrap.css"/>
    <link rel="stylesheet" href="css/admin.css"/>
    <link rel="shortcut icon" href="https://pic1.imgdb.cn/item/635d4e7f16f2c2beb1394e9a.png">
    <script src="js/jquery.js"></script>
    <script src="js/jquery.validate.min.js"></script>
    <script src="js/messages_zh.min.js"></script>
    <script>
        $(document).ready(function () {
            // 项目一开始就要初始化验证
            $("#useradd").validate({
                errorElement: 'span',
                errorClass: 'error-block',
                onfocusout: function (element) {
                    $(element).valid();
                },
                rules: {
                    username: {
                        required: true
                    },
                    email: {
                        required: true,
                        email: true
                    },
                    password: {
                        required: true,
                        passWord: true    // 和自定义验证相同
                    },
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
                    username: {
                        required: "请输入用户名",
                    },
                    email: {
                        required: "请输入邮箱",
                        email: "请输入正确格式的邮箱"
                    },
                    password: {
                        required: "请输入密码",
                        passWord: "请输入至少10位密码（至少包含1个字母，1个数字和1个特殊字符）",
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

            // 自定义密码验证
            jQuery.validator.addMethod("passWord", function (value, element) {
                var passWord = /^(?=.*?[A-Za-z])(?=.*?[0-9])(?=.*((?=[\x21-\x7e]+)[^A-Za-z0-9])).{10,}$/;
                return this.optional(element) || (passWord.test(value));
            }, "请输入至少10位密码（至少包含1个字母，1个数字和1个特殊字符）");
            // 自定义验证邮箱
            jQuery.validator.addMethod("email", function (value, element) {
                var email = /^([a-zA-Z]|[0-9])(\w|-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
                return this.optional(element) || (email.test(value));
            }, "请输入正确格式的邮箱");
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


    <c:if test="${!empty failMsg }">
        <div class="alert alert-danger">${failMsg }</div>
    </c:if>

    <br><br>
    <form id="useradd" class="form-horizontal" action="${pageContext.request.contextPath }/admin/user_add"
          method="post">
        <div class="form-group">
            <label for="username" class="col-sm-1 control-label">用户名</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" id="username" name="username" required="required"
                       value="${u.username }"/>
            </div>
        </div>
        <div class="form-group">
            <label for="email" class="col-sm-1 control-label">邮箱</label>
            <div class="col-sm-6">
                <input type="email" class="form-control" id="email" name="email" required="required"
                       value="${u.email }"/>
            </div>
        </div>
        <div class="form-group">
            <label for="password" class="col-sm-1 control-label">密码</label>
            <div class="col-sm-6">
                <input type="password" class="form-control" id="password" name="password" required="required"
                       value="${u.password }"/>
            </div>
        </div>
        <div class="form-group">
            <label for="name" class="col-sm-1 control-label">收货人</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" id="name" name="name" value="${u.name }"/>
            </div>
        </div>
        <div class="form-group">
            <label for="phone" class="col-sm-1 control-label">电话</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" id="phone" name="phone" value="${u.phone }"/>
            </div>
        </div>
        <div class="form-group">
            <label for="address" class="col-sm-1 control-label">地址</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" id="address" name="address" value="${u.address }"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-1 col-sm-10">
                <button type="submit" class="btn btn-success">提交保存</button>
            </div>
        </div>
    </form>

    <span style="color:red;"></span>
</div>

</body>
</html>