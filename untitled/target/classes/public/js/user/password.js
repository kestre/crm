layui.use(['form','jquery'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery;

    form.on('submit(saveBtn)', function(data) {
        $.post(
            "user/updatePwd",
            {
                oldPassword: data.field.oldPwd,
                newPassword: data.field.newPwd,
                repeatPassword: data.field.repeatPwd
            },
            function (data) {
                // 成功
                if (data.code == 200) {
                    // 清空cookie，并返回到登录页面
                    layer.msg(data.msg, function (){
                        $.removeCookie("userId",{domain:serverName,path:crm});
                        $.removeCookie("userName",{domain:serverName,path:crm});
                        $.removeCookie("trueName",{domain:serverName,path:crm});
                        window.parent.location.href = "index";
                    });
                } else {    // 失败
                    layer.msg(data.msg,{icon: 5});
                }
            }
        );
    });

});