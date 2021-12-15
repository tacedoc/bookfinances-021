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
    <title>图书借阅</title>
</head>
<body>
<div class="page-content clearfix">
    <div id="Member_Ratings">
        <div class="d_Confirm_Order_style">

            <div class="search_style">
                <ul class="search_content clearfix">
                    <form action="book/showBookList">
                        <input type="hidden" name="delFlag" value="${delFlag}"/>
                        <li>
                            <label class="l_f" for="bookName_search">书名</label>
                            <input name="bookName" id="bookName_search" type="text" class="text_add"
                                   placeholder="输入图书名含有的字符" value="${bookCondition.name}"
                                   style=" width:200px"/>
                        </li>
                        <li>
                            <label class="l_f" for="author_search">作者</label>
                            <input name="author" id="author_search" type="text" class="text_add"
                                   placeholder="输入作者含有的字符" value="${bookCondition.author}"
                                   style=" width:200px"/>
                        </li>
                        <li>
                            <label class="l_f" for="author_search">厂商</label>
                            <input name="vendor" id="vendor_search" type="text" class="text_add"
                                   placeholder="输入厂商含有的字符" value="${bookCondition.vendor}"
                                   style=" width:200px"/>
                        </li>
                        <li style="width:90px;">
                            <input type="submit" class="btn_search" value="查询"/>
                        </li>
                    </form>
                </ul>
            </div>

            <div class="border clearfix">
                <span class="l_f">
                 图书信息 共：<b>${pageInfo.totalCount}</b> 条记录
                </span>
            </div>

            <div class="table_menu_list">
                <table class="table table-striped table-bordered table-hover" id="sample-table">
                    <thead>
                    <tr>
                        <th width="80">ID</th>
                        <th width="150">图书名称</th>
                        <th width="100">作者</th>
                        <th width="100">厂商</th>
                        <th width="80">剩余库存</th>
                        <th width="80">单价(:元)</th>
                        <th width="80">借阅价格(:元/天)</th>
                        <th width="80">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${bookList}" var="book" varStatus="status">
                        <tr>
                            <td>${book.id}</td>
                            <td>${book.name}</td>
                            <td>${book.author}</td>
                            <td>${book.vendor}</td>
                            <td>${book.remainCount}</td>
                            <td>${book.unitPrice}</td>
                            <td>${book.borrowPrice}</td>
                            <td class="td-manage">
                                <c:forEach items="${buttonList}" var="button">
                                    <a title="${button.name}" onclick="${button.url}" href="javascript:void(0)"
                                       class="btn btn-xs btn-info">${button.icon}</a>
                                </c:forEach>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <div class="row">
                    <div class="col-md-6">
                        <div id="sample-table_info" class="dataTables_info">
                            当前页数：${pageInfo.currentPage} 总页数：${pageInfo.totalPageNum}
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div id="sample-table_paginate" class="dataTables_paginate paging_bootstrap">
                            <ul class="pagination">
                                <a href="book/showBookList?currentPage=1&bookName=${bookCondition.name}&author=${bookCondition.author}&vendor=${vendor}&delFlag=${delFlag}">首页</a>
                                <c:if test="${pageInfo.currentPage > 1}">
                                    <a href="book/showBookList?currentPage=${pageInfo.currentPage - 1}&bookName=${bookCondition.name}&author=${bookCondition.author}&vendor=${vendor}&delFlag=${delFlag}">上一页</a>
                                </c:if>
                                <c:if test="${pageInfo.currentPage < pageInfo.totalPageNum}">
                                    <a href="book/showBookList?currentPage=${pageInfo.currentPage + 1}&bookName=${bookCondition.name}&author=${bookCondition.author}&vendor=${vendor}&delFlag=${delFlag}">下一页</a>
                                </c:if>
                                <a href="book/showBookList?currentPage=${pageInfo.totalPageNum}&bookName=${bookCondition.name}&author=${bookCondition.author}&vendor=${vendor}&delFlag=${delFlag}">尾页</a>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--图书借阅图层-->
<div class="borrow_book_style" id="borrow_book" style="display:none">
    <ul class=" page-content">
        <br>
        <li>
            <label class="label_name">书名：</label><span class="add_name">
                <input id="bookName_borrow" value="" type="text" class="text_add" readonly/></span>
            <div class="prompt r_f"></div>
        </li>
        <br>
        <li>
            <label class="label_name">作者：</label><span class="add_name">
                <input id="author_borrow" value="" type="text" class="text_add" readonly/></span>
            <div class="prompt r_f"></div>
        </li>
        <br>
        <li>
            <label class="label_name">借阅价格(元/天)：</label><span class="add_name">
                <input id="borrowPrice_borrow" value="" type="text" class="text_add" readonly/></span>
            <div class="prompt r_f"></div>
        </li>
        <br>
        <li>
            <label class="label_name">约定借阅天数：</label><span class="add_name">
                <input id="promiseDay_borrow" value="" type="text" class="text_add"
                       placeholder="请输入借阅天数"/></span><span id="tip_promiseDay" style="color: red"></span>
            <div class="prompt r_f"></div>
        </li>
        <br>
        <li>
            <label class="label_name">总借阅费用(元)：</label><span class="add_name">
                <input id="cost_borrow" value="" type="text" class="text_add" readonly/></span>
            <div class="prompt r_f"></div>
        </li>
    </ul>
</div>
<!--图书修改图层-->
<div class="edit_book_style" id="edit_book" style="display:none">
    <ul class=" page-content">
        <br>
        <li>
            <label class="label_name">书名：</label><span class="add_name">
                <input id="bookName_edit" value="" type="text" class="text_add" readonly/></span>
            <div class="prompt r_f"></div>
        </li>
        <br>
        <li>
            <label class="label_name">作者：</label><span class="add_name">
                <input id="author_edit" value="" type="text" class="text_add" readonly/></span>
            <div class="prompt r_f"></div>
        </li>
        <br>
        <li>
            <label class="label_name">单价(元)：</label><span class="add_name">
                <input id="unitPrice_edit" value="" type="text" class="text_add"/></span>
            <div class="prompt r_f"></div>
        </li>
        <br>
        <li>
            <label class="label_name">借阅价格(元/天)：</label><span class="add_name">
                <input id="borrowPrice_edit" value="" type="text" class="text_add"/></span>
            <div class="prompt r_f"></div>
        </li>
    </ul>
</div>
</body>
</html>
<script>

    /*图书借阅*/
    function book_borrow(obj) {
        var bookId = $(obj).parents("tr").find("td").eq(0).text();
        var bookName = $(obj).parents("tr").find("td").eq(1).text();
        var author = $(obj).parents("tr").find("td").eq(2).text();
        var remainCount = $(obj).parents("tr").find("td").eq(4).text();
        var borrowPrice = $(obj).parents("tr").find("td").eq(6).text();

        $("#bookName_borrow").val(bookName);
        $("#author_borrow").val(author);
        $("#borrowPrice_borrow").val(borrowPrice);

        layer.open({
            type: 1,
            title: '图书借阅',
            maxmin: true,
            shadeClose: false, //点击遮罩关闭层
            area: ['800px', ''],
            content: $('#borrow_book'),
            btn: ['提交', '取消'],
            yes: function (index) {
                if (1 > remainCount) {
                    layer.msg('借阅失败，剩余库存不足!', {icon: 2, time: 2000});
                    return false;
                }

                var promiseDay = $("#promiseDay_borrow").val();
                var cost = $("#cost_borrow");
                var dateReg = /^[1-9]\d*$/;
                if (!dateReg.test(promiseDay)) {
                    $("#tip_promiseDay").text("借阅天数需为大于0的整数！");
                    return false;
                }
                cost.val(borrowPrice * promiseDay);

                $.ajax({
                    type: "GET",
                    cache: false,
                    url: "borrow/borrowVerify",
                    data: {
                        bookId: bookId,
                        promiseDay: promiseDay
                    },
                    datatype: "text",
                    success: function (msg) {
                        if (msg === 'success') {
                            layer.msg('借阅成功!', {icon: 1, time: 1000});
                            //layer.close(index);
                            setTimeout(function () {
                                window.location.reload();
                            }, 1000);
                        } else {
                            layer.msg(msg, {icon: 2, time: 2000});
                        }
                    }
                });
            }
        });
    }

    /*图书修改*/
    function book_edit(obj) {
        var bookId = $(obj).parents("tr").find("td").eq(0).text();
        var bookName = $(obj).parents("tr").find("td").eq(1).text();
        var author = $(obj).parents("tr").find("td").eq(2).text();
        var unitPrice = $(obj).parents("tr").find("td").eq(5).text();
        var borrowPrice = $(obj).parents("tr").find("td").eq(6).text();

        $("#bookName_edit").val(bookName);
        $("#author_edit").val(author);
        $("#unitPrice_edit").val(unitPrice);
        $("#borrowPrice_edit").val(borrowPrice);

        layer.open({
            type: 1,
            title: '图书修改',
            maxmin: true,
            shadeClose: false, //点击遮罩关闭层
            area: ['800px', ''],
            content: $('#edit_book'),
            btn: ['提交', '取消'],
            yes: function (index) {

                var priceReg = /^\d*.?\d+$/;
                if (!priceReg.test($("#unitPrice_edit").val()) || !priceReg.test($("#borrowPrice_edit").val())) {
                    alert("价格需为 大于0 的整数");
                    return false;
                }

                $.ajax({
                    type: "GET",
                    cache: false,
                    url: "book/editBookInfo",
                    data: {
                        bookId: bookId,
                        unitPrice: $("#unitPrice_edit").val(),
                        borrowPrice: $("#borrowPrice_edit").val()
                    },
                    datatype: "text",
                    success: function (msg) {
                        if (msg === 'success') {
                            layer.msg('修改成功!', {icon: 1, time: 1000});
                            //layer.close(index);
                            setTimeout(function () {
                                window.location.reload();
                            }, 1000);
                        } else {
                            layer.msg(msg, {icon: 2, time: 2000});
                        }
                    }
                });
            }
        });
    }

    /*图书下架*/
    function book_down(obj) {
        var bookId = $(obj).parents("tr").find("td").eq(0).text();
        $.ajax({
            type: "GET",
            cache: false,
            url: "book/bookDown",
            data: {
                bookId: bookId,
            },
            datatype: "text",
            success: function (msg) {
                if (msg === 'success') {
                    layer.msg('下架成功!', {icon: 1, time: 1000});
                    //layer.close(index);
                    setTimeout(function () {
                        window.location.reload();
                    }, 1000);
                } else {
                    layer.msg(msg, {icon: 2, time: 2000});
                }
            }
        });
    }

    /*图书上架*/
    function book_up(obj) {
        var bookId = $(obj).parents("tr").find("td").eq(0).text();
        $.ajax({
            type: "GET",
            cache: false,
            url: "book/bookUp",
            data: {
                bookId: bookId,
            },
            datatype: "text",
            success: function (msg) {
                if (msg === 'success') {
                    layer.msg('上架成功!', {icon: 1, time: 1000});
                    //layer.close(index);
                    setTimeout(function () {
                        window.location.reload();
                    }, 1000);
                } else {
                    layer.msg(msg, {icon: 2, time: 2000});
                }
            }
        });
    }

    laydate({
        elem: '#start',
        event: 'focus'
    });

</script>