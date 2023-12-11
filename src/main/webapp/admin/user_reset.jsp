<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>重置密码</title>
    <meta charset="utf-8"/>
    <link rel="stylesheet" href="css/bootstrap.css"/>
    <script src="js/jquery.js"></script>
    <script src="js/jquery.validate.min.js"></script>
    <script src="js/messages_zh.min.js"></script>
    <script>
        $(document).ready(function () {
            // 项目一开始就要初始化验证
            $("#userreset").validate({
                errorElement: 'span',
                errorClass: 'error-block',
                onfocusout: function (element) {
                    $(element).valid();
                },
                rules: {
                    password: {
                        required: true,
                        passWord: true    // 和自定义验证相同
                    }
                },
                messages: {
                    password: {
                        required: "请输入密码",
                        passWord: "请输入至少10位密码（至少包含1个字母，1个数字和1个特殊字符）",
                    }
                }
            })

            // 自定义密码验证
            jQuery.validator.addMethod("passWord", function (value, element) {
                var passWord = /^(?=.*?[A-Za-z])(?=.*?[0-9])(?=.*((?=[\x21-\x7e]+)[^A-Za-z0-9])).{10,}$/;
                return this.optional(element) || (passWord.test(value));
            }, "请输入至少10位密码（至少包含1个字母，1个数字和1个特殊字符）");
        })

    </script>
</head>
<body>
<div class="container-fluid">

    <jsp:include page="/admin/header.jsp"/>

    <br><br>

    <form id="userreset" class="form-horizontal" action="${pageContext.request.contextPath }/admin/user_reset"
          method="post">
        <input type="hidden" name="id" value="${param.id }">
        <div class="form-group">
            <label for="username" class="col-sm-1 control-label">用户名</label>
            <div id="username" class="col-sm-5">${param.username }</div>
        </div>
        <div class="form-group">
            <label for="email" class="col-sm-1 control-label">邮箱</label>
            <div id="email" class="col-sm-5">${param.email }</div>
        </div>
        <div class="form-group">
            <label for="password" class="col-sm-1 control-label">密码</label>
            <div class="col-sm-6">
                <input type="password" class="form-control" id="password" name="password" value="" required="required">
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