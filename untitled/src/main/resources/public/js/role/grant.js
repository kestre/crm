layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    // 加载树形结构，扩大作用域
    var zTreeObj;
    loadtree();

    /**
     * 渲染zTree树
     */
    function loadtree() {
        let setting = {
            // 开启复选框
            check: {
                enable: true
            },
            // 数据来源使用简单json格式
            data: {
                simpleData: {
                    enable: true
                }
            },
            // 定义复选框被点击时触发的方法
            callback: {
                onCheck: zTreeOnCheck
            }
        };
        // zTree 的数据属性
        $.get(
            "module/queryAllModules",
            {
                roleId: $("[name='roleId']").val()
            },
            function (data) {
                $(document).ready(function(){
                    zTreeObj = $.fn.zTree.init($("#zTree"), setting, data);
                });
            }
        );
    }

    /**
     * 获取被选中的节点数据
     * @param event
     * @param treeId
     * @param treeNode
     */
    function zTreeOnCheck(event, treeId, treeNode) {
        // 获取所有被选中的节点数据
        let checkedNodes = zTreeObj.getCheckedNodes(true);
        let ids = '';

        if (checkedNodes.length > 0) {
            $.each(checkedNodes, function (index, node) {
                if (index == checkedNodes.length - 1) {
                    ids += 'ids=' + node.id;
                } else {
                    ids += 'ids=' + node.id + '&';
                }
            });
        }
        $.post(
            "role/addGrant?roleId=" + $("[name='roleId']").val() + '&' + ids,
            {},
            function (data) {
                if (data.code == 200) {

                } else {
                    layer.msg(data.msg, {icon: 5});
                }
            }
        );
    }
});