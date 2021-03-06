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
                    <form action="factoryBook/showFactoryBookList">
<%--
                        <input type="hidden" name="factoryId" value="${factoryBookCondition.id}" />
--%>
                        <li>
                            <label class="l_f" for="factoryBookName_search">??????</label>
                            <input name="factoryBookName" id="factoryBookName_search" type="text" class="text_add"
                                   placeholder="????????????????????????????????????" value="${factoryBook.name}"
                                   style=" width:200px"/>
                        </li>
                        <li>
                            <label class="l_f" for="author_search">??????</label>
                            <input name="author" id="author_search" type="text" class="text_add"
                                   placeholder="???????????????????????????" value="${factoryBook.author}"
                                   style=" width:200px"/>
                        </li>
                        <li>
                            <label class="l_f" for="factoryName_search">??????</label>
                            <input name="factoryName" id="factoryName_search" type="text" class="text_add"
                                   placeholder="???????????????????????????" value="${factoryBook.factoryName}"
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
                        <th width="150">????????????</th>
                        <th width="100">??????</th>
                        <th width="80">??????(:???)</th>
                        <th width="100">??????</th>
                        <th width="80">??????</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${factoryBookList}" var="factoryBook" varStatus="status">
                        <tr>
                            <td>${factoryBook.id}</td>
                            <td>${factoryBook.name}</td>
                            <td>${factoryBook.author}</td>
                            <td>${factoryBook.unitPrice}</td>
                            <td>${factoryBook.factoryName}</td>
                            <td class="td-manage">
                                <c:forEach items="${buttonList}" var="button">
                                    <a title="${button.name}" onclick="${button.url}" href="javascript:void(0)"
                                       class="btn btn-xs btn-info">${button.icon}</a>
                                </c:forEach>
                                <%--<a title="??????" onclick="purchase_factoryBook(this)" href="javascript:void(0)"
                                   class="btn btn-xs btn-info"><i class="icon-book bigger-120"></i></a>--%>
                            </td>
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
                                <a href="factoryBook/showFactoryBookList?currentPage=1&factoryBookName=${factoryBook.name}&author=${factoryBook.author}&factoryName=${factoryBook.factoryName}">??????</a>
                                <c:if test="${pageInfo.currentPage > 1}">
                                    <a href="factoryBook/showFactoryBookList?currentPage=${pageInfo.currentPage - 1}&factoryBookName=${factoryBook.name}&author=${factoryBook.author}&factoryName=${factoryBook.factoryName}">?????????</a>
                                </c:if>
                                <c:if test="${pageInfo.currentPage < pageInfo.totalPageNum}">
                                    <a href="factoryBook/showFactoryBookList?currentPage=${pageInfo.currentPage + 1}&factoryBookName=${factoryBook.name}&author=${factoryBook.author}&factoryName=${factoryBook.factoryName}">?????????</a>
                                </c:if>
                                <a href="factoryBook/showFactoryBookList?currentPage=${pageInfo.totalPageNum}&factoryBookName=${factoryBook.name}&author=${factoryBook.author}&factoryName=${factoryBook.factoryName}">??????</a>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--??????????????????-->
<div class="purchase_factoryBook_style" id="purchase_factoryBook" style="display:none">
    <ul class=" page-content">
        <br>
        <li>
            <label class="label_name">?????????</label><span class="add_name">
                <input id="factoryBookName_purchase" value="" type="text" class="text_add" readonly/></span>
            <div class="prompt r_f"></div>
        </li>
        <br>
        <li>
            <label class="label_name">?????????</label><span class="add_name">
                <input id="author_purchase" value="" type="text" class="text_add" readonly/></span>
            <div class="prompt r_f"></div>
        </li>
        <br>
        <li>
            <label class="label_name">?????????</label><span class="add_name">
                <input id="unitPrice_purchase" value="" type="text" class="text_add" readonly/></span>
            <div class="prompt r_f"></div>
        </li>
        <br>
        <li>
            <label class="label_name">?????????</label><span class="add_name">
                <input id="factoryName_purchase" value="" type="text" class="text_add" readonly/></span>
            <div class="prompt r_f"></div>
        </li>
        <br>
        <li>
            <label class="label_name">???????????????</label><span class="add_name">
                <input id="count" value="" type="text" class="text_add"/></span>
            <div class="prompt r_f"></div>
        </li>
        <br>
        <li>
            <label class="label_name">???????????????</label><span class="add_name">
                <input id="sell_price" value="" type="text" class="text_add"/></span>
            <div class="prompt r_f"></div>
        </li>
        <br>
        <li>
            <label class="label_name">????????????(???/???)???</label><span class="add_name">
                <input id="borrow_price" value="" type="text" class="text_add"/></span>
            <div class="prompt r_f"></div>
        </li>
    </ul>
</div>
</body>
</html>
<script>
    /*????????????*/
    function purchase_factoryBook(obj) {
        var factoryBookId = $(obj).parents("tr").find("td").eq(0).text();
        var factoryBookName = $(obj).parents("tr").find("td").eq(1).text();
        var author = $(obj).parents("tr").find("td").eq(2).text();
        var unitPrice = $(obj).parents("tr").find("td").eq(3).text();
        var factoryName = $(obj).parents("tr").find("td").eq(4).text();

        $("#factoryBookName_purchase").val(factoryBookName);
        $("#author_purchase").val(author);
        $("#unitPrice_purchase").val(unitPrice);
        $("#factoryName_purchase").val(factoryName);
        layer.open({
            type: 1,
            title: '????????????',
            maxmin: true,
            shadeClose: false, //?????????????????????
            area: ['800px', ''],
            content: $('#purchase_factoryBook'),
            btn: ['??????', '??????'],
            yes: function (index) {
                var count = $("#count").val();
                var sellPrice = $("#sell_price").val();
                var borrowPrice = $("#borrow_price").val();

                var countReg = /^[1-9]\d*$/;

                var priceReg = /^\d*.?\d+$/;

                if (!countReg.test(count)){
                    alert("????????????????????? ??????0 ????????????");
                    return false;
                }
                if (!priceReg.test(sellPrice) || unitPrice > sellPrice){
                    alert("????????????????????? ?????? ????????????????????????");
                    return false;
                }
                if (!priceReg.test(borrowPrice) ){
                    alert("????????????????????? ??????0 ????????????");
                    return false;
                }


                $.ajax({
                    type: "GET",
                    cache: false,
                    url: "purchase/purchaseFactoryBook",
                    data: {
                        factoryBookId: factoryBookId,
                        factoryBookName: factoryBookName,
                        author: author,
                        unitPrice: unitPrice,
                        factoryName: factoryName,
                        count: count,
                        sellPrice: sellPrice,
                        borrowPrice: borrowPrice
                    },
                    datatype: "text",
                    success: function (msg) {
                        if (msg === 'success') {
                            layer.msg('????????????!', {icon: 1, time: 1000});
                            //window.location = location;
                            layer.close(index);
                        } else {
                            layer.msg('????????????!', {icon: 2, time: 2000});
                        }
                    }
                });
            }
        });
    }

    laydate({
        elem: '#start',
        event: 'focus'
    });

</script>