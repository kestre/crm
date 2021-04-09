layui.use(['table','layer'],function() {
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    /**
     * 渲染客户开发计划表格数据
     */
    var tableIns = table.render({
        elem: '#customerServeList', // 表格绑定的ID
        url : 'customer_serve/list?state=fw_001', // 访问数据的地址
        cellMinWidth : 95,
        page : true, // 开启分页
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar:"#toolbarDemo",
        id : "customerServeListTable",
        cols : [[
            {field: "id", title:'编号',fixed:"true"},
            {field: 'customer', title: '客户名',  align:'center'},
            {field: 'dicValue', title: '服务类型', align:'center'},
            {field: 'overview', title: '概要', align:'center'},
            {field: 'createPeople', title: '创建人',  align:'center'},
            {field: 'createDate', title: '创建时间', align:'center'},
            {field: 'updateDate', title: '更新时间', align:'center'},
        ]]
    });

    // 点击搜索按钮事件
    $(".search_btn").click(function () {
        // 重新渲染表格
        table.reload('customerServeListTable', {
            where: {
                customer: $("[name='customer']").val()
                ,serveType: $("#type").val()
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    });

    // 表单右侧工具栏
    table.on('toolbar(customerServes)', function(obj){
        if(obj.event == "add"){
            openCAddCustomerServeDialog("<h3>创建服务</h3>","customer_serve/toCustomerServeAddPage");
        }
    });

    // 开启新窗口
    function openCAddCustomerServeDialog(title, url) {
        title = "<h2>" + title + "</h2>";
        layui.layer.open({
            type: 2,
            title: title,
            shadeClose: true,
            shade: 0.6,
            area: ['750px', '550px'],
            content: url,
            // 最大化最小化
            maxmin: true,
            // 不允许窗口拉伸
            resize: false
        });
    }

});