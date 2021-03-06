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
    <title>????????????????????????</title>
</head>

<body>


<div class="page-content clearfix">
    <div id="Member_Ratings">
        <div class="d_Confirm_Order_style">

            <div class="search_style">
                <ul class="search_content clearfix">
                    <form action="borrow/showBorrowRecordList">
                        <li>
                            <label class="l_f" for="startDate_search">????????????</label>
                            <input name="startDate" id="startDate_search" type="text" class="text_add"
                                   placeholder="??????:yyyy-MM-dd" value="${startDate}"
                                   style=" width:200px"/>
                        </li>
                        <li>
                            <label class="l_f" for="endDate_search">????????????</label>
                            <input name="endDate" id="endDate_search" type="text" class="text_add"
                                   placeholder="??????:yyyy-MM-dd" value="${endDate}"
                                   style=" width:200px"/>
                        </li>

                        <li>
                            <label class="l_f" for="returnStatus">????????????</label>
                            <select name="returnStatus" id="returnStatus" style="width: 200px">
                                <option value="" selected>-- ????????? --</option>
                                <option value="0"
                                        <c:if test="${borrowVoCondition.returnStatus == 0}">selected</c:if>
                                >?????????
                                </option>
                                <option value="1"
                                        <c:if test="${borrowVoCondition.returnStatus == 1}">selected</c:if>
                                >?????????
                                </option>
                                <option value="2"
                                        <c:if test="${borrowVoCondition.returnStatus == 2}">selected</c:if>
                                >?????????
                                </option>
                                <option value="3"
                                        <c:if test="${borrowVoCondition.returnStatus == 3}">selected</c:if>
                                >??????????????????
                                </option>
                                <option value="4"
                                        <c:if test="${borrowVoCondition.returnStatus == 4}">selected</c:if>
                                >??????
                                </option>
                            </select>
                        </li>

                        <li style="width:90px;">
                            <input type="submit" class="btn_search" value="??????"/>
                        </li>
                    </form>
                </ul>
            </div>

            <div class="border clearfix">
                <span class="l_f">
                 ???????????? ??????<b>${pageInfo.totalCount}</b> ?????????
                </span>
            </div>

            <div class="table_menu_list">
                <table class="table table-striped table-bordered table-hover" id="sample-table">
                    <thead>
                    <tr>
                        <th width="80">ID</th>
                        <th width="80">?????????</th>
                        <th width="100">?????????</th>
                        <th width="100">????????????</th>
                        <th width="80">??????????????????</th>
                        <th width="100">??????????????????</th>
                        <th width="80">??????</th>
                        <th width="80">????????????</th>

                        <th width="150">??????</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${borrowVoList}" var="borrowVo" varStatus="status">
                        <tr>
                            <td>${borrowVo.id}</td>
                            <td>${borrowVo.username}</td>
                            <td>${borrowVo.bookName}</td>
                            <td><fmt:formatDate value="${borrowVo.borrowDate}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
                            <td>${borrowVo.promiseDay}</td>
                            <td><fmt:formatDate value="${borrowVo.returnDate}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
                            <td>${borrowVo.cost}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${borrowVo.returnStatus == 0}">?????????</c:when>
                                    <c:when test="${borrowVo.returnStatus == 1}">?????????</c:when>
                                    <c:when test="${borrowVo.returnStatus == 2}">?????????</c:when>
                                    <c:when test="${borrowVo.returnStatus == 3}">??????????????????</c:when>
                                    <c:when test="${borrowVo.returnStatus == 4}">??????</c:when>
                                </c:choose>
                            </td>
                            <td class="td-manage">
                                <c:if test="${borrowVo.returnStatus <= 1}">
                                    <c:forEach items="${buttonList}" var="button">
                                        <a title="${button.name}" onclick="${button.url}" href="javascript:void(0)"
                                           class="btn btn-xs btn-info">${button.icon}</a>
                                    </c:forEach>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <div class="row">
                    <div class="col-md-6">
                        <div id="sample-table_info" class="dataTables_info">
                            ???????????????${pageInfo.currentPage} ????????????${pageInfo.totalPageNum}
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div id="sample-table_paginate" class="dataTables_paginate paging_bootstrap">
                            <ul class="pagination">
                                <a href="borrow/showBorrowRecordList?currentPage=1&returnStatus=${borrowVoCondition.returnStatus}&startDate=${borrowVoCondition.startDate}&endDate=${borrowVoCondition.endDate}">??????</a>
                                <c:if test="${pageInfo.currentPage > 1}">
                                    <a href="borrow/showBorrowRecordList?currentPage=${pageInfo.currentPage - 1}&returnStatus=${borrowVoCondition.returnStatus}&startDate=${borrowVoCondition.startDate}&endDate=${borrowVoCondition.endDate}">?????????</a>
                                </c:if>
                                <c:if test="${pageInfo.currentPage < pageInfo.totalPageNum}">
                                    <a href="borrow/showBorrowRecordList?currentPage=${pageInfo.currentPage + 1}&returnStatus=${borrowVoCondition.returnStatus}&startDate=${borrowVoCondition.startDate}&endDate=${borrowVoCondition.endDate}">?????????</a>
                                </c:if>
                                <a href="borrow/showBorrowRecordList?currentPage=${pageInfo.totalPageNum}&returnStatus=${borrowVoCondition.returnStatus}&startDate=${borrowVoCondition.startDate}&endDate=${borrowVoCondition.endDate}">??????</a>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<!--??????????????????-->
<div class="postpone_borrow_style" id="postpone_borrow" style="display:none">
    <ul class=" page-content">
        <br>
        <li>
            <label class="label_name">?????????</label><span class="add_name">
                <input id="bookName_postpone" value="" type="text" class="text_add" readonly/></span>
            <div class="prompt r_f"></div>
        </li>
        <br>
        <li>
            <label class="label_name">??????????????????</label><span class="add_name">
                <input id="postponeDays_postpone" value="" type="text" class="text_add"/></span>
            <div class="prompt r_f"></div>
        </li>
    </ul>
</div>
</body>
</html>

<script>

    /*??????????????????*/
    function book_return(obj) {
        var borrowId = $(obj).parents("tr").find("td").eq(0).text();
        $.ajax({
            type: "GET",
            cache: false,
            url: "borrow/bookReturn",
            data: {
                borrowId: borrowId
            },
            datatype: "text",
            success: function (msg) {
                if (msg === 'success') {
                    layer.msg('????????????!', {icon: 1, time: 1000});
                    setTimeout(function () {
                        window.location.reload();
                    }, 1000);
                } else {
                    layer.msg(msg, {icon: 2, time: 2000});
                }
            }
        });
    }

    /*??????????????????*/
    function book_postpone(obj) {
        var borrowId = $(obj).parents("tr").find("td").eq(0).text();
        var bookName = $(obj).parents("tr").find("td").eq(2).text();

        $("#bookName_postpone").val(bookName);

        layer.open({
            type: 1,
            title: '????????????',
            maxmin: true,
            shadeClose: false, //?????????????????????
            area: ['800px', ''],
            content: $('#postpone_borrow'),
            btn: ['??????', '??????'],
            yes: function (index) {

                var dateReg = /^[1-9]\d*$/;

                if (!dateReg.test($("#postponeDays_postpone").val())) {
                    alert("?????????????????? ??????0 ?????????");
                    return false;
                }

                $.ajax({
                    type: "GET",
                    cache: false,
                    url: "borrow/bookPostpone",
                    data: {
                        borrowId: borrowId,
                        postponeDays: $("#postponeDays_postpone").val()
                    },
                    datatype: "text",
                    success: function (msg) {
                        if (msg === 'success') {
                            layer.msg('????????????!', {icon: 1, time: 1000});
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

    /*????????????*/
    function book_damage(obj) {
        var borrowId = $(obj).parents("tr").find("td").eq(0).text();
        $.ajax({
            type: "GET",
            cache: false,
            url: "borrow/bookDamage",
            data: {
                borrowId: borrowId,
            },
            datatype: "text",
            success: function (msg) {
                if (msg === 'success') {
                    layer.msg('????????????!', {icon: 1, time: 1000});
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

