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
    <title>????????????</title>
</head>
<body>
<div class="page-content clearfix">
    <div id="Member_Ratings">
        <div class="d_Confirm_Order_style">

            <div class="search_style">
                <ul class="search_content clearfix">
                    <form action="user/userInfoListShow">
                        <li>
                            <label class="l_f" for="account_search">??????</label>
                            <input name="account" id="account_search" type="text" class="text_add"
                                   placeholder="????????????????????????" value="${userVoCondition.account}"
                                   style=" width:200px"/>
                        </li>
                        <li>
                            <label class="l_f" for="phone_search">?????????</label>
                            <input name="phone" id="phone_search" type="text" class="text_add"
                                   placeholder="???????????????????????????" value="${userVoCondition.phone}"
                                   style=" width:200px"/>
                        </li>
                        <li>
                            <label class="l_f" for="available_search">??????</label>
                            <select name="available" id="available_search" style="width: 200px">
                                <option value="" selected>-- ????????? --</option>
                                <option value="true"
                                        <c:if test="${userVoCondition.available == true}">selected</c:if>
                                >??????</option>
                                <option value="false"
                                        <c:if test="${userVoCondition.available == false}">selected</c:if>
                                >??????</option>
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
                        <th width="80">??????</th>
                        <th width="100">?????????</th>
                        <th width="100">??????</th>
                        <th width="80">????????????</th>
                        <th width="80">????????????</th>
                        <th width="80">??????</th>
                        <th width="100">??????</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${userInfoList}" var="userInfo" varStatus="status">
                        <tr>
                            <td>${userInfo.id}</td>
                            <td>${userInfo.account}</td>
                            <td>${userInfo.phone}</td>
                            <td>${userInfo.username}</td>
                            <td>${userInfo.balance}</td>
                            <td>${userInfo.roleName}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${userInfo.available == true}">?????????</c:when>
                                    <c:when test="${userInfo.available == false}">?????????</c:when>
                                </c:choose>
                            </td>
                            <td class="td-manage">
                                <c:if test="${userInfo.roleId != 1}">
                                    <c:choose>
                                        <c:when test="${userInfo.available == true}">
                                            <a title="??????" onclick="toDisable(this)" href="javascript:void(0)"
                                               class="btn btn-danger"></a>
                                        </c:when>
                                        <c:when test="${userInfo.available == false}">
                                            <a title="??????" onclick="toEnable(this)" href="javascript:void(0)"
                                               class="btn btn-group"></a>
                                        </c:when>
                                    </c:choose>
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
                                <a href="user/userInfoListShow?currentPage=1&account=${userVoCondition.account}&phone=${userVoCondition.phone}&available=${userVoCondition.available}">??????</a>
                                <c:if test="${pageInfo.currentPage > 1}">
                                    <a href="user/userInfoListShow?currentPage=${pageInfo.currentPage - 1}&account=${userVoCondition.account}&phone=${userVoCondition.phone}&available=${userVoCondition.available}">?????????</a>
                                </c:if>
                                <c:if test="${pageInfo.currentPage < pageInfo.totalPageNum}">
                                    <a href="user/userInfoListShow?currentPage=${pageInfo.currentPage + 1}&account=${userVoCondition.account}&phone=${userVoCondition.phone}&available=${userVoCondition.available}">?????????</a>
                                </c:if>
                                <a href="user/userInfoListShow?currentPage=${pageInfo.totalPageNum}&account=${userVoCondition.account}&phone=${userVoCondition.phone}&available=${userVoCondition.available}">??????</a>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
<script>

    /*??????*/
    function toDisable(obj) {
        var loginId = $(obj).parents("tr").find("td").eq(0).text();
        $.ajax({
            type: "GET",
            cache: false,
            url: "login/toDisable",
            data: {
                loginId: loginId,
            },
            datatype: "text",
            success: function (msg) {
                if (msg === 'success') {
                    layer.msg('????????????!', {icon: 1, time: 1000});
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

    /*??????*/
    function toEnable(obj) {
        var loginId = $(obj).parents("tr").find("td").eq(0).text();
        $.ajax({
            type: "GET",
            cache: false,
            url: "login/toEnable",
            data: {
                loginId: loginId,
            },
            datatype: "text",
            success: function (msg) {
                if (msg === 'success') {
                    layer.msg('????????????!', {icon: 1, time: 1000});
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