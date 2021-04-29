layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    // 点击取消按钮时关闭当前弹出层
    $("#closeBtn").click(function () {
        // $("input").attr("readonly", "readonly");
        // $("#edit-btn").hide();
        // $("#cancel-btn").show();
        return "user/toSettingPage?id=" + $("[name='id']").val();
    });
    $("#edit").click(function () {
        $("input").removeAttr("readonly");
        $("#edit-btn").show();
        $("#cancel-btn").hide();
        return false;
    });

    // 表单提交监听事件
    form.on('submit(addOrUpdateUser)', function (data) {
        // 数据加载遮罩层
        let msg = layer.msg("数据提交中，请稍后", {
            // 图标
            icon: 16,
            // 关闭时间，不关闭
            time: false
        });
        let url =  "user/updateMsg";

        // 发送请求
        $.post(
            url,
            data.field,
            function (data) {
                // 关闭数据加载遮罩层
                layer.close(msg);
                // 请求返回成功
                if (data.code == 200) {
                    layer.msg(data.msg, {icon: 6});
                } else {
                    // 请求返回失败
                    layer.msg(data.msg,{icon: 5});
                }
            }
        );
        // 阻止表单提交
        return false;
    });

    /**
     * 加载角色下拉框
     */
    layui.formSelects.config("selectId", {
        type: "get",    // 请求方式
        searchUrl: "role/queryAllRoles?userId=" + $("[name='id']").val(), // 请求地址
        keyName: "roleName",    // 显示的文本
        keyVal: "id"    // 下拉框的val值
    });
    
});