<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>交往记录</title>
	<#include "../common.ftl">
</head>
<body class="childrenBody">

<form class="layui-form" >
	<blockquote class="layui-elem-quote quoteBox">
		<form class="layui-form">
			<div class="layui-inline">
				<div class="layui-input-inline">
					<select name="customer" id="cusName" lay-search>
						<option value="">请选择</option>
					</select>
				</div>
				<a class="layui-btn search_btn" data-type="reload"><i class="layui-icon">&#xe615;</i> 搜索</a>
			</div>
		</form>
	</blockquote>
	<table id="customerContactList" class="layui-table"  lay-filter="customerContact"></table>

	<script type="text/html" id="toolbarDemo">
		<div class="layui-btn-container">
			<a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">
				<i class="layui-icon">&#xe608;</i>
				添加
			</a>
			<a class="layui-btn layui-btn-normal delNews_btn layui-bg-red" lay-event="del">
				<i class="layui-icon">&#xe616</i>
				删除
			</a>
		</div>
	</script>

	<!--操作-->
	<script id="customerContactListBar" type="text/html">
		<a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>
		<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
	</script>
</form>

<script type="text/javascript" src="js/contact/customerContact.js"></script>

</body>
</html>