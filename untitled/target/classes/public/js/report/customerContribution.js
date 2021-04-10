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
        elem: '#customerContributionList', // 表格绑定的ID
        url : 'customer/queryCustomerContributionByParams', // 访问数据的地址
        cellMinWidth : 95,
        page : true, // 开启分页
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        id : "customerContributionListTable",
        cols : [[
            {field: 'name', title: '客户名',  align:'center'},
            {field: 'total', title: '总金额（￥）', align:'center'},
        ]]
    });

    // 点击搜索按钮事件
    $(".search_btn").click(function () {
        // 重新渲染表格
        table.reload('customerContributionListTable', {
            where: {
                customerName: $("[name='customerName']").val()
                ,type: $("#type").val()
                ,time:$("input[name='time']").val()
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    });

});