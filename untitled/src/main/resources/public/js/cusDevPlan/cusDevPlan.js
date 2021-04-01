layui.use(['table','layer'],function() {
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    /**
     * 渲染客户开发计划表格数据
     */
    var tableIns = table.render({
        elem: '#cusDevPlanList', // 表格绑定的ID
        url : 'saleChance/list?flag=1', // 访问数据的地址
        cellMinWidth : 95,
        page : true, // 开启分页
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        id : "cusDevPlanTable",
        cols : [[
            {field: "id", title:'编号',fixed:"true"},
            {field: 'chanceSource', title: '机会来源',align:"center"},
            {field: 'customerName', title: '客户名称',  align:'center'},
            {field: 'successRatio', title: '成功几率', align:'center'},
            {field: 'overview', title: '概要', align:'center'},
            {field: 'linkMan', title: '联系人',  align:'center'},
            {field: 'linkPhone', title: '联系电话', align:'center'},
            {field: 'description', title: '描述', align:'center'},
            {field: 'createMan', title: '创建人', align:'center'},
            {field: 'createDate', title: '创建时间', align:'center'},
            {field: 'updateDate', title: '修改时间', align:'center'},
            {field: 'devResult', title: '开发状态', fixed:'right',templet:function (d) {
                return formatterDevResult(d.devResult);
            }},
            {title: '操作', templet:'#op',fixed:"right",align:"center", minWidth:150}
        ]]
    });

    /**
     * 格式化开发状态
     *  0 - 未开发
     *  1 - 开发中
     *  2 - 开发成功
     *  3 - 开发失败
     * @param value
     * @returns {string}
     */
    function formatterDevResult(value){
        if(value == 0) {
            return "<div style='color: orange'>未开发</div>";
        } else if(value==1) {
            return "<div style='color: #00FF00;'>开发中</div>";
        } else if(value==2) {
            return "<div style='color: #00B83F'>开发成功</div>";
        } else if(value==3) {
            return "<div style='color: red'>开发失败</div>";
        } else {
            return "<div style='color: #af0000'>未知</div>"
        }
    }

    // 格式化

    // 点击搜索按钮事件
    $(".search_btn").click(function () {
        // 重新渲染表格
        table.reload('cusDevPlanTable', {
            where: {
                customerName: $("[name='customerName']").val().trim()
                ,createMan: $("[name='createMan']").val().trim()
                ,devResult: $("#devResult").val()
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    });

    // 表单右侧工具栏
    table.on('tool(cusDevPlans)', function(obj){
        // console.log("obj", obj);
        switch(obj.event){
            // 开发
            case 'dev':
                openCusDevOlanDialog("客户开发计划 - 计划项数据开发", "cusDevPlan/toCusDevPlan?id=" + obj.data.id);
                break;
            // 详情
            case 'info':
                openCusDevOlanDialog("客户开发计划 - 计划项数据维护", "cusDevPlan/toCusDevPlan?id=" + obj.data.id);
                break;
        };
    });

    // 开启新窗口
    function openCusDevOlanDialog(title, url) {
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