$(document).ready(function () {
    /*登录按钮单击触发的事件*/
    $("#login_btn").on("click",
        function () {
            var account = $("#login_account");
            var password = $("#login_password");
            if (account.val() === '' || password.val() === '') {
                alert("账号或密码不能为空！");
                return false;
            }
            $.ajax({
                type: "POST",
                url: "login/loginVerify",
                data: {
                    account: account.val(),
                    password: password.val()
                },
                /*dataType建议手动加上，否则收到的数据类型可能会有出入*/
                dataType: "json",
                async: true,
                success: function (msg) {
                    if (msg != null) {
                        if (msg.available){
                            location.href = "login/showIndex";
                        }else {
                            alert("账号已被禁用");
                        }
                    } else {
                        alert("账号或密码错误！");
                    }
                }
            });
        }
    );

    /*手机号文本框单击时，删除提示内容*/
    $("#registry_phone").on("click", function () {
        $("#phone_tip").text('');
    });

    /*账号文本框单击时，删除提示内容*/
    $("#registry_account").on("click", function () {
        $("#account_tip").text('');
    });

    /*注册按钮单击触发的事件*/
    $("#registry_btn").on("click",
        function () {
            var phone = $("#registry_phone");
            var account = $("#registry_account");
            var password_one = $("#registry_password_one");
            var password_two = $("#registry_password_two");
            var username = $("#registry_username");

            if (phone.val() === '' || account.val() === '' ||
                password_one.val() === '' || password_two.val() === '' || username.val() === '') {
                alert("信息填写不完整！");
                return false;
            }

            var phoneReg = /^1\d{10}$/;
            if (!phoneReg.test(phone.val())){
                alert("手机号必须为1开头的11为数字格式！");
                return false;
            }

            if (password_one.val() !== password_two.val()) {
                alert("两次输入的密码不一致");
                return false;
            }

            var registry_phoneExist = false;
            var registry_accountExist = false;
            registry_phoneExist = phoneCheckForAjax(registry_phoneExist);
            registry_accountExist = accountCheckForAjax(registry_accountExist);

            if (registry_phoneExist || registry_accountExist) {
                return false;
            }
            //$("#reg_form").submit();
            $.ajax({
                type: "POST",
                cache: false,
                url: "registry",
                data: {
                    phone: phone.val(),
                    account: account.val(),
                    password: password_one.val(),
                    username: username.val()
                },
                async: false,
                success: function (msg) {
                    if (msg === "success") {
/*                        setTimeout(function(){
                            alert('注册成功')
                        }, 1500);*/
                        alert("注册成功！");
                        location.href = "login/showIndex";
                    } else {
                        alert("注册失败！");
                    }
                }
            });

        });
});

/*Ajax请求检验手机号是否存在*/
var phoneCheckForAjax = function (registry_phoneExist) {
    $.ajax({
        type: "POST",
        cache: false,
        url: "registry/phoneExistCheck",
        data: {
            phone: $("#registry_phone").val()
        },
        dataType: "json",
        /*此处设置为同步请求，否则服务器还未传回数据，就执行后面的代码了，造成手机号存在却依旧注册成功。*/
        async: false,
        success: function (msg) {
            if (msg != null) {
                registry_phoneExist = true;
                $("#phone_tip").text("该手机号已被使用");
            } else {
                registry_phoneExist = false;
            }
        }
    });
    return registry_phoneExist;
};

/*Ajax请求检验登录账号是否存在*/
var accountCheckForAjax = function (registry_accountExist) {

    $.ajax({
        type: "POST",
        cache: false,
        url: "registry/accountExistCheck",
        data: {
            account: $("#registry_account").val()
        },
        dataType: "json",
        /*此处设置为同步请求，否则服务器还未传回数据，就执行后面的代码了，造成账号存在却依旧注册成功。*/
        async: false,
        success: function (msg) {
            if (msg != null) {
                registry_accountExist = true;
                $("#account_tip").text("该账号已被注册");
            } else {
                registry_accountExist = false;
            }
        }
    });
    return registry_accountExist;
};


