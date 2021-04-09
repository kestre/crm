layui.use(['table','layer'],function() {
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    /**
     * 渲染客户开发计划表格数据
     */
    var tableIns = table.render({
        elem: '#customerServeList', // 表格绑定的ID
        url : 'customer_serve/list?state=fw_005&flag=1', // 访问数据的地址
        cellMinWidth : 95,
        page : true, // 开启分页
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        id : "customerServeListTable",
        cols : [[
            {field: "id", title:'编号',fixed:"true"},
            {field: 'customer', title: '客户名',  align:'center'},
            {field: 'dicValue', title: '服务类型', align:'center'},
            {field: 'overview', title: '概要', align:'center'},
            {field: 'createPeople', title: '创建人',  align:'center'},
            {field: 'assignTime', title: '分配时间', align:'center'},
            {field: 'assigner', title: '分配人', align:'center'},
            {field: 'serviceProcePeople', title: '处理人', align:'center'},
            {field: 'serviceProce', title: '处理内容', align:'center'},
            {field: 'serviceProceTime', title: '处理时间', align:'center'},
            {field: 'serviceProceResult', title: '处理结果', align:'center'},
            {field: 'satisfied', title: '满意度', align:'center'},
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

});