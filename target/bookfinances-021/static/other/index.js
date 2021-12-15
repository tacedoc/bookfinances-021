$(function () {
    var cid = $('#nav_list> li>.submenu');
    cid.each(function (i) {
        $(this).attr('id', "Sort_link_" + i);

    })
});
jQuery(document).ready(function () {
    $.each($(".submenu"), function () {
        var $aobjs = $(this).children("li");
        var rowCount = $aobjs.size();
        var divHeigth = $(this).height();
        $aobjs.height(divHeigth / rowCount);
    });
    //初始化宽度、高度

    $("#main-container").height($(window).height() - 76);
    $("#iframe").height($(window).height() - 140);

    $(".sidebar").height($(window).height() - 99);
    var thisHeight = $("#nav_list").height($(window).outerHeight() - 173);
    $(".submenu").height();
    $("#nav_list").children(".submenu").css("height", thisHeight);

    //当文档窗口发生改变时 触发
    $(window).resize(function () {
        $("#main-container").height($(window).height() - 76);
        $("#iframe").height($(window).height() - 140);
        $(".sidebar").height($(window).height() - 99);

        var thisHeight = $("#nav_list").height($(window).outerHeight() - 173);
        $(".submenu").height();
        $("#nav_list").children(".submenu").css("height", thisHeight);
    });
    $(document).on('click', '.iframeurl', function () {
        var cid = $(this).attr("name");
        var cname = $(this).attr("title");
        $("#iframe").attr("src", cid).ready();
        $("#Bcrumbs").attr("href", cid).ready();
        $(".Current_page a").attr('href', cid).ready();
        $(".Current_page").attr('name', cid);
        $(".Current_page").html(cname).css({"color": "#333333", "cursor": "default"}).ready();
        $("#parentIframe").html('<span class="parentIframe iframeurl"> </span>').css("display", "none").ready();
        $("#parentIfour").html('').css("display", "none").ready();
    });
});

$(document).on('click', '.link_cz > li', function () {
    $('.link_cz > li').removeClass('active');
    $(this).addClass('active');
});

/*********************点击事件*********************/
$(document).ready(function () {
    $('#nav_list,.link_cz').find('li.home').on('click', function () {
        $('#nav_list,.link_cz').find('li.home').removeClass('active');
        $(this).addClass('active');
    });

    //时间设置
    function currentTime() {
        var d = new Date(), str = '';
        str += d.getFullYear() + '年';
        str += d.getMonth() + 1 + '月';
        str += d.getDate() + '日';
        str += d.getHours() + '时';
        str += d.getMinutes() + '分';
        str += d.getSeconds() + '秒';
        return str;
    }

    setInterval(function () {
        $('#time').html(currentTime)
    }, 1000);
    $('#Exit_system').on('click', function () {
        layer.confirm('是否确定退出系统？', {
                btn: ['是', '否'],//按钮
                icon: 2,
            },
            function () {
                location.href = "login.html";
            });
    });
});

function link_operating(name, title) {
    var cid = $(this).name;
    var cname = $(this).title;
    $("#iframe").attr("src", cid).ready();
    $("#Bcrumbs").attr("href", cid).ready();
    $(".Current_page a").attr('href', cid).ready();
    $(".Current_page").attr('name', cid);
    $(".Current_page").html(cname).css({"color": "#333333", "cursor": "default"}).ready();
    $("#parentIframe").html('<span class="parentIframe iframeurl"> </span>').css("display", "none").ready();
    $("#parentIfour").html('').css("display", "none").ready();
}

try {
    ace.settings.check('navbar', 'fixed')
} catch (e) {
}


try {
    ace.settings.check('main-container', 'fixed')
} catch (e) {
}


try {
    ace.settings.check('sidebar', 'fixed')
} catch (e) {
}


$("#menu_style").niceScroll({
    cursorcolor: "#888888",
    cursoropacitymax: 1,
    touchbehavior: false,
    cursorwidth: "5px",
    cursorborder: "0",
    cursorborderradius: "5px"
});



try {
    ace.settings.check('sidebar', 'collapsed')
} catch (e) {
}




try {
    ace.settings.check('breadcrumbs', 'fixed')
} catch (e) {
}