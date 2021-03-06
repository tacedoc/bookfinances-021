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
                    <form action="report/showReportList" id="report_search">
                        <li>
                            <label class="l_f" for="year_search">??????</label>
                            <input name="year" id="year_search" type="text" class="text_add"
                                   placeholder="??????????????????" value="${year}"
                                   style=" width:200px"/>
                        </li>
                        <li>
                            <label class="l_f" for="month_search">??????</label>
                            <input name="month" id="month_search" type="text" class="text_add"
                                   placeholder="??????????????????" value="${month}"
                                   style=" width:200px"/>
                        </li>
                        <li style="width:90px;">
                            <input type="submit" class="btn_search" id="report_search_btn" value="??????"/>
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
                        <th width="100">??????</th>
                        <th width="100">????????????</th>
                        <th width="100">????????????</th>
                        <th width="100">????????????</th>
                        <%--<th width="100">??????</th>--%>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${reportVoList}" var="reportVo" varStatus="status">
                        <tr>
                            <td>${reportVo.id}</td>
                            <td><fmt:formatDate value="${reportVo.reportDate}" pattern="yyyy-MM"/></td>
                            <td>${reportVo.monthGet}</td>
                            <td>${reportVo.monthOut}</td>
                            <td>${reportVo.profitAndLoss}</td>
                            <%--<td>
                                <a class="btn btn-group" href="javaScript:void(0)">??????execl??????</a>
                            </td>--%>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <div class="row">
                    <div class="col-md-6">
                        <div id="sample-table_info" class="dataTables_info">
                            ???????????????${pageInfo.currentPage}  ????????????${pageInfo.totalPageNum}
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div id="sample-table_paginate" class="dataTables_paginate paging_bootstrap">
                            <ul class="pagination">
                                <a href="report/showReportList?currentPage=1&year=${year}&month=${month}">??????</a>
                                <c:if test="${pageInfo.currentPage > 1}">
                                    <a href="report/showReportList?currentPage=${pageInfo.currentPage - 1}&year=${year}&month=${month}">?????????</a>
                                </c:if>
                                <c:if test="${pageInfo.currentPage < pageInfo.totalPageNum}">
                                    <a href="report/showReportList?currentPage=${pageInfo.currentPage + 1}&year=${year}&month=${month}">?????????</a>
                                </c:if>
                                <a href="report/showReportList?currentPage=${pageInfo.totalPageNum}&year=${year}&month=${month}">??????</a>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%--<script>
    $(document).ready(function () {
        $("#report_search_btn").on("click",function () {
            var year = $("#year_search").val();
            var month = $("#month_search").val();
            if (year === "" || month === ""){
                alert("??????????????????????????????");
                return false;
            }

            $("#report_search").submit();
        })
    });
</script>--%>
</body>
</html>

