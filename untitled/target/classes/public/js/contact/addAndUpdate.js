layui.use(['form', 'layer', 'laydate'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;
    var laydate = layui.laydate;

    //执行一个laydate实例
    laydate.render({
        elem: '#contactTime', //指定元素
    });

    $.post(
        crm + "/customer/queryCustomers",
        {},
        function (data) {
            $.each(data, function (index, element) {
                // 渲染指派人下拉框
                let cusId = $("#OriginCusId").val();
                if (element.id == cusId) {
                    $("#cusId").append(new Option(element.name, element.id, false,true));
                } else {
                    $("#cusId").append(new Option(element.name, element.id));
                }
            });
            // 必须重新渲染下拉框
            form.render("select");
        }
    );

    // 点击取消按钮时关闭当前弹出层
    $("#closeBtn").click(function () {
        // 获取当前iframe层的索引
        let index = parent.layer.getFrameIndex(window.name);
        // 关闭当前弹出层
        parent.layer.close(index);
    });

    // 表单提交监听事件
    form.on('submit(addOrUpdateLinkman)', function (data) {
        // 数据加载遮罩层
        let msg = layer.msg("数据提交中，请稍后", {
            // 图标
            icon: 16,
            // 关闭时间，不关闭
            time: false
        });

        let url = crm + "/contact/add";
        let contactId = $("[name='id']").val();

        if (contactId != null && contactId != '') {

            url = crm + "/contact/update";
        }

        console.log(data.field)
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
                    // 延迟关闭子窗口
                    setTimeout(function () {
                        // 重新渲染父页面表单
                        parent.location.reload("form");
                        // 关闭当前弹出层
                        layer.close("iframe");
                    },800);
                } else {
                    // 请求返回失败
                    layer.msg(data.msg,{icon: 5});
                }
            }
        );

        // 阻止表单提交
        return false;
    });


    
});