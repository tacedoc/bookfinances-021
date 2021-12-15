<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>登录页面</title>

    <link href="static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="static/myself/css/login.css" rel="stylesheet">

    <script src="static/myself/js/jquery-3.4.1.min.js"></script>
    <script src="static/bootstrap/js/bootstrap.min.js"></script>
    <script src="static/myself/js/login.js"></script>
</head>

<body>
<div class="container">
    <form class="form-signin">
        <h2 class="form-signin-heading">登录页面</h2>
        <div class="form-group">
            <label class="control-label" for="login_account">账号</label>
            <input class="form-control" type="text" name="account" placeholder="请输入账号" id="login_account">
        </div>

        <div class="form-group">
            <label class="control-label" for="login_password">密码</label>
            <input class="form-control" type="password" name="password" placeholder="请输入密码" id="login_password">
        </div>

        <input class="btn btn-lg btn-primary btn-block" type="button" value="登录" id="login_btn">
        <span id="show_registry" data-toggle="modal" data-target="#myModal">没有账号?立即注册</span>
    </form>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">注册页面</h4>
            </div>
            <div class="modal-body">
                <%--<form class="form-horizontal" role="form" action="registry" method="post" id="reg_form">
                --%>    <div class="form-group">
                        <label class="control-label" for="registry_phone">手机号</label><span class="tip_sty" id="phone_tip"></span>
                        <input class="form-control" type="text" name="phone" placeholder="请输入手机号" id="registry_phone">
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="registry_account">账号</label><span class="tip_sty" id="account_tip"></span>
                        <input class="form-control" type="text" name="account" placeholder="请输入账号" id="registry_account">
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="registry_password_one">密码</label>
                        <input class="form-control" type="password" name="password" placeholder="请输入密码" id="registry_password_one">
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="registry_password_two">确认密码</label>
                        <input class="form-control" type="password" name="password" placeholder="请确认密码" id="registry_password_two">
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="registry_username">昵称</label>
                        <input class="form-control" type="text" name="username" placeholder="请输入昵称" id="registry_username">
                    </div>
               <%-- </form>--%>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">返回登录</button>
                <button type="button" class="btn btn-primary" id="registry_btn">注册</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
