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
    <title>??????????????????</title>
</head>

<body>



<div class="page-content clearfix">
    <div id="Member_Ratings">
        <div class="d_Confirm_Order_style">

            <div class="search_style">
                <ul class="search_content clearfix">
                    <form action="tradeFlow/showTradeFlowRecordList">
                        <li>
                            <label class="l_f" for="payer_search">?????????</label>
                            <input name="payer" id="payer_search" type="text" class="text_add"
                                   placeholder="????????????????????????????????????" value="${tradeFlowVoCondition.payer}"
                                   style=" width:200px"/>
                        </li>

                        <li>
                            <label class="l_f" for="tradeType">????????????</label>
                            <select name="tradeType" id="tradeType" style="width: 200px">
                                <option value="" selected>-- ????????? --</option>
                                <option value="????????????"
                                    <c:if test="${tradeFlowVoCondition.tradeType == '????????????'}">selected</c:if>
                                >????????????</option>
                                <option value="????????????"
                                    <c:if test="${tradeFlowVoCondition.tradeType == '????????????'}">selected</c:if>
                                >????????????</option>
                                <option value="????????????"
                                    <c:if test="${tradeFlowVoCondition.tradeType == '????????????'}">selected</c:if>
                                >????????????</option>
                                <option value="????????????"
                                    <c:if test="${tradeFlowVoCondition.tradeType == '????????????'}">selected</c:if>
                                >????????????</option>
                            </select>
                        </li>
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
                        <th width="100">?????????</th>
                        <th width="100">?????????</th>
                        <th width="100">????????????</th>
                        <th width="100">????????????</th>
                        <th width="100">????????????</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${tradeFlowVoList}" var="tradeFlowVo" varStatus="status">
                        <tr>
                            <td>${tradeFlowVo.id}</td>
                            <td>${tradeFlowVo.payer}</td>
                            <td>${tradeFlowVo.payee}</td>
                            <td>${tradeFlowVo.tradeType}</td>
                            <td>${tradeFlowVo.tradeAmount}</td>
                            <td><fmt:formatDate value="${tradeFlowVo.tradeDate}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
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
                                <a href="tradeFlow/showTradeFlowRecordList?currentPage=1&payer=${tradeFlowVoCondition.payer}&tradeType=${tradeFlowVoCondition.tradeType}&startDate=${tradeFlowVoCondition.startDate}&endDate=${tradeFlowVoCondition.endDate}">??????</a>
                                <c:if test="${pageInfo.currentPage > 1}">
                                    <a href="tradeFlow/showTradeFlowRecordList?currentPage=${pageInfo.currentPage - 1}&payer=${tradeFlowVoCondition.payer}&tradeType=${tradeFlowVoCondition.tradeType}&startDate=${tradeFlowVoCondition.startDate}&endDate=${tradeFlowVoCondition.endDate}">?????????</a>
                                </c:if>
                                <c:if test="${pageInfo.currentPage < pageInfo.totalPageNum}">
                                    <a href="tradeFlow/showTradeFlowRecordList?currentPage=${pageInfo.currentPage + 1}&payer=${tradeFlowVoCondition.payer}&tradeType=${tradeFlowVoCondition.tradeType}&startDate=${tradeFlowVoCondition.startDate}&endDate=${tradeFlowVoCondition.endDate}">?????????</a>
                                </c:if>
                                <a href="tradeFlow/showTradeFlowRecordList?currentPage=${pageInfo.totalPageNum}&payer=${tradeFlowVoCondition.payer}&tradeType=${tradeFlowVoCondition.tradeType}&startDate=${tradeFlowVoCondition.startDate}&endDate=${tradeFlowVoCondition.endDate}">??????</a>
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
