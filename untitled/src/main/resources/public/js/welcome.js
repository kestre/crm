layui.use(['echarts','layer'],function() {
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        echarts = layui.echarts;

    $.ajax({
        type:"get",
        url:crm + "/customer/countCustomerLevelGroup",
        dataType:"json",
        success:function (data) {
            var chartDom = document.getElementById('make');
            var myChart = echarts.init(chartDom);
            var option;

            option = {
                xAxis: {
                    type: 'category',
                    data: data.dataX,
                },
                yAxis: {
                    type: 'value'
                },
                series: [{
                    data: data.dataY,
                    type: 'line'
                }]
            };

            option && myChart.setOption(option);
        }

    })

    $.ajax({
        type:"get",
        url:crm + "/customer/countCustomerLevelGroup2",
        dataType:"json",
        success:function (data) {
            var chartDom = document.getElementById('make2');
            var myChart = echarts.init(chartDom);
            var option;

            option = {
                title: {
                    text: '客户构成分析',
                    subtext: '来自公司',
                    left: 'center'
                },
                tooltip: {
                    trigger: 'item',
                    formatter: '{a} <br/>{b} : {c} ({d}%)'
                },
                legend: {
                    left: 'center',
                    top: 'bottom',
                    data: data.data1,
                },
                toolbox: {
                    show: true,
                    feature: {
                        mark: {show: true},
                        dataView: {show: true, readOnly: false},
                        restore: {show: true},
                        saveAsImage: {show: true}
                    }
                },
                series: [
                    {
                        name: '半径模式',
                        type: 'pie',
                        radius: [20, 140],
                        center: ['25%', '50%'],
                        roseType: 'radius',
                        itemStyle: {
                            borderRadius: 5
                        },
                        label: {
                            show: false
                        },
                        emphasis: {
                            label: {
                                show: true
                            }
                        },
                        data: data.data2,
                    },
                    {
                        name: '面积模式',
                        type: 'pie',
                        radius: [20, 140],
                        center: ['75%', '50%'],
                        roseType: 'area',
                        itemStyle: {
                            borderRadius: 5
                        },
                        data: data.data2,
                    }
                ]
            };

            option && myChart.setOption(option);
        }

    })

});