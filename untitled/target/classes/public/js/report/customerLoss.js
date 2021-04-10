layui.use(['table','layer',"laydate"],function() {
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    // 渲染日期选择框
    layui.laydate.render({
        elem:'#time'
    });

    /**
     * 渲染客户开发计划表格数据
     */
    var tableIns = table.render({
        elem: '#customerLossList', // 表格绑定的ID
        url : 'loss/list?state=1', // 访问数据的地址
        cellMinWidth : 95,
        page : true, // 开启分页
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        id : "customerContributionListTable",
        cols : [[
            {field: 'cusNum', title: '客户编号',  align:'center'},
            {field: 'cusName', title: '客户名称', align:'center'},
            {field: 'cusManager', title: '客户经理',  align:'center'},
            {field: 'lastOrderTime', title: '最后下单时间', align:'center'},
            {field: 'lossReason', title: '流失原因',  align:'center'},
            {field: 'confirmLossTime', title: '确认流失时间', align:'center'},
        ]]
    });

    // 点击搜索按钮事件
    $(".search_btn").click(function () {
        // 重新渲染表格
        table.reload('customerContributionListTable', {
            where: {
                cusName: $("[name='cusName']").val()
                ,cusNum: $("[name='cusNum']").val()
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    });

});