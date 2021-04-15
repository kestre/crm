layui.use(['table','layer'],function() {
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;


    /**
     * 渲染客户开发计划表格数据
     */
    var tableIns = table.render({
        elem: '#customerLinkmanList', // 表格绑定的ID
        url : 'linkman/list', // 访问数据的地址
        cellMinWidth : 95,
        page : true, // 开启分页
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "customerLinkmanListTable",
        cols : [[
            {type: "checkbox", fixed:"center"},
            {field: "id", title:'编号', sort: true, fixed:"true"},
            {field: 'linkName', title: '联系人名称', align:'center'},
            {field: 'gender', title: '性别',  align:'center'},
            {field: 'vocation', title: '职位', align:'center'},
            {field: 'officePhone', title: '办公电话',  align:'center'},
            {field: 'phone', title: '联系号码',  align:'center'},
            {field: 'name', title: '所属客户', align:'center'},
            {title: '操作', templet:'#customerLinkmanListBar',fixed:"right",align:"center", minWidth:150}
        ]]
    });

    // 点击搜索按钮事件
    $(".search_btn").click(function () {
        // 重新渲染表格
        table.reload('customerLinkmanListTable', {
            where: {
                linkName: $("[name='linkName']").val()
                ,gender: $("[name='gender']").val()
                ,name: $("[name='name']").val()
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    });
    // 为表格的新增和修改页面添加事件
    table.on('toolbar(customerLinkman)', function(obj){
        switch(obj.event){
            // 添加事件
            case 'add':
                // 开启子窗口
                openCustomerLinkmanDialog("添加联系人", "linkman/toAddAndUpdatePage");
                break;
            // 批量删除事件
            case 'del':
                // 获取被选中的数据
                // let checkStatus = table.checkStatus("saleChanceListTable");
                let checkStatus = table.checkStatus(obj.config.id);
                // console.log(checkStatus.data);
                if (checkStatus.data.length < 1) {
                    layer.msg("未选中任何数据", {icon: 5});
                } else {
                    // 将选中的数据的id封装
                    let ids = "";
                    $.each(checkStatus.data, function (index, item) {
                        if (index == checkStatus.data.length - 1) {
                            ids += "ids=" + item.id;
                        }else {
                            ids += "ids=" + item.id + "&";
                        }
                    });
                    layer.confirm('选中删除行数：<span style="color: orange">'+checkStatus.data.length+'</span><br/>', {
                        icon: 3,
                        title: '营销机会管理 - 批量删除'
                    }, function (index) {
                        layer.close(index);
                        $.post(
                            "saleChance/delete?" + ids,
                            {},
                            function (data) {
                                if (data.code == 200) {
                                    layer.msg(data.msg);
                                    // 重载表格
                                    tableIns.reload();
                                } else {
                                    layer.msg(data.msg, {icon: 5});
                                }
                            }
                        );
                    });
                }
                break;
        };
    });

    // 表单右侧工具栏
    table.on('tool(customerLinkman)', function(obj){
        switch(obj.event){
            // 修改事件
            case 'edit':
                openCustomerLinkmanDialog("销售机会管理 - 修改营销机会", "linkman/toAddAndUpdatePage?id=" + obj.data.id);
                break;
            // 删除事件
            case 'del':
                layer.confirm('确认删除？', {
                    icon: 3,
                    title: '营销机会管理 - 单行删除'
                }, function(index){
                    layer.close(index);
                    $.post(
                        "saleChance/delete",
                        {
                            ids: obj.data.id
                        },
                        function (data) {
                            if (data.code == 200) {
                                layer.msg(data.msg,{icon: 6});
                                // 重载表格
                                tableIns.reload();
                            }else{
                                layer.msg(data.msg,{icon: 5});
                            }
                        }
                    );
                });
                break;
        };
    });

    // 开启新窗口
    function openCustomerLinkmanDialog(title, url) {
        title = "<h2>" + title + "</h2>";
        layui.layer.open({
            type: 2,
            title: title,
            shadeClose: true,
            shade: 0.6,
            area: ['500px', '620px'],
            content: url,
            // 最大化最小化
            maxmin: true,
            // 不允许窗口拉伸
            resize: false
        });
    }

});