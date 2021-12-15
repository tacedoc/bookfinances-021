<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="assets/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="css/style.css"/>
    <link href="assets/css/codemirror.css" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/ace.min.css"/>
    <link rel="stylesheet" href="assets/css/font-awesome.min.css"/>
    <!--[if IE 7]>
    <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css"/>
    <![endif]-->
    <!--[if lte IE 8]>
    <link rel="stylesheet" href="assets/css/ace-ie.min.css"/>
    <![endif]-->
    <script src="assets/js/jquery.min.js"></script>
    <!-- <![endif]-->
    <!--[if IE]>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <![endif]-->
    <!--[if !IE]> -->
    <script type="text/javascript">
        window.jQuery || document.write("<script src='assets/js/jquery-2.0.3.min.js'>" + "<" + "/script>");
    </script>
    <!-- <![endif]-->
    <!--[if IE]>
    <script type="text/javascript">
        window.jQuery || document.write("<script src='assets/js/jquery-1.10.2.min.js'>" + "<" + "/script>");
    </script>
    <![endif]-->
    <script type="text/javascript">
        if ("ontouchend" in document) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>" + "<" + "/script>");
    </script>
    <script src="assets/js/bootstrap.min.js"></script>
    <script src="assets/js/typeahead-bs2.min.js"></script>
    <script src="assets/js/jquery.dataTables.min.js"></script>
    <script src="assets/js/jquery.dataTables.bootstrap.js"></script>
    <script type="text/javascript" src="js/H-ui.js"></script>
    <script type="text/javascript" src="js/H-ui.admin.js"></script>
    <script src="assets/layer/layer.js" type="text/javascript"></script>
    <script src="assets/laydate/laydate.js" type="text/javascript"></script>
    <title>余额充值</title>
</head>
<body>
<div class="page-content clearfix">
    <ul class=" page-content">
        <br/>
        <li>
            <label class="l_f" for="availableBalance">可用余额：</label>
            <input id="availableBalance" type="text" value="${availableBalance}"
                   placeholder="可用余额" readonly style=" width:200px"/>
        </li>
        <br/>
        <li>
            <label class="l_f" for="balance_edit">充值余额：</label>
            <input id="balance_edit" type="text"
                   placeholder="充值余额" style=" width:200px"/>
        </li>
        <br/>
        <br/>
        <li style="width:200px;">
            <input type="button" class="btn btn-group" value="提交" id="edit_btn"/>
        </li>
    </ul>
</div>
</body>
</html>
<script>
    $("#edit_btn").click(function () {
        var availableBalance = parseFloat($("#availableBalance").val());
        var balance = $("#balance_edit").val();
        var reg = /^\d*.?\d+$/;

        if (!reg.test(balance) ){
            alert("充值金额必须为 大于0 的数字！");
            return false;
        }

        $.ajax({
            type: "POST",
            cache: false,
            url: "user/rechargeBalance",
            data: {
                balance: balance
            },
            datatype: "text",
            success: function (msg) {
                if (msg === 'success') {

                    $('#availableBalance').val(availableBalance + parseFloat(balance));
                    layer.msg('充值成功!', {icon: 1, time: 1000});
                    layer.close(index);
                } else {
                    layer.msg('充值失败!', {icon: 2, time: 2000});
                }
            }
        });
    })
</script>