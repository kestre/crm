<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>首页</title>
    <#include "common.ftl">
    <link rel="stylesheet" href="css/welcome.css" media="all">
</head>
<body class="childrenBody">
<div class="layui-card card1">
    <div class="layui-row">
        <div class="layui-col-md3">
            <div class="layui-text">新增客户</div>
            <div class="layui-text data">${(newCusCount)!}人</div>
        </div>
        <div class="layui-col-md3">
            <div class="layui-text">联系客户</div>
            <div class="layui-text data">${(recentContactTime)!}次</div>
        </div>
        <div class="layui-col-md3">
            <div class="layui-text">合作客户</div>
            <div class="layui-text data">${(cooperateCus)!}人</div>
        </div>
        <div class="layui-col-md3">
            <div class="layui-text">新增合同</div>
            <div class="layui-text data">${(newOrderCount)!}份</div>
        </div>
    </div>
</div>
<div class="layui-card">
    <div class="layui-card-header">客户构成分析</div>
    <div class="layui-card-body" id="make2" style="width: 100%;height: 400px"></div>
</div>
<div class="layui-row layui-col-space15">
    <div class="layui-col-md6">
        <div class="layui-card">
            <div class="layui-card-header">客户构成分析</div>
            <div class="layui-card-body" id="make" style="width: 100%;height: 200px"></div>
        </div>
    </div>
    <div class="layui-col-md6">
        <div class="layui-card">
            <div class="layui-card-header">客户构成分析</div>
            <div class="layui-card-body" id="make" style="width: 100%;height: 200px"></div>
        </div>
    </div>
</div>

<script type="text/javascript" src="js/welcome.js"></script>

</body>
</html>