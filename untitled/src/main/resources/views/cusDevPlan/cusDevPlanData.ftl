<!DOCTYPE html>
<html>
	<head>
		<title>客户开发计划管理</title>
		<#include "../common.ftl">
	</head>
	<body class="childrenBody">
	<div class="layui-col-md12">
		<div class="layui-card">
			<div class="layui-card-body">
				<form class="layui-form" >
					<#-- 营销机会的ID -->
					<input name="id" type="hidden" value="${(saleChance.id)!}"/>
					<#-- 开发状态 -->
					<input id="devResult" type="hidden" value="${(saleChance.devResult)!}"/>
					<div class="layui-form-item layui-row">
						<div class="layui-col-xs6">
							<label class="layui-form-label">客户名称</label>
							<div class="layui-input-block">
								<input type="text" class="layui-input"
									   name="customerName" id="customerName"  value="${(saleChance.customerName)!}" readonly="readonly">
							</div>
						</div>
						<div class="layui-col-xs6">
							<label class="layui-form-label">机会来源</label>
							<div class="layui-input-block">
								<input type="text" class="layui-input"
									   name="chanceSource" id="chanceSource" value="${(saleChance.chanceSource)!}" readonly="readonly">
							</div>
						</div>
					</div>

					<div class="layui-form-item layui-row">
						<div class="layui-col-xs6">
							<label class="layui-form-label">联系人</label>
							<div class="layui-input-block">
								<input type="text" class="layui-input"
									   name="linkMan"  lay-verify="required"  value="${(saleChance.linkMan)!}" readonly="readonly">
							</div>
						</div>
						<div class="layui-col-xs6">
							<label class="layui-form-label">联系电话</label>
							<div class="layui-input-block">
								<input type="text" class="layui-input"
									   lay-verify="phone" name="linkPhone" value="${(saleChance.linkPhone)!}" readonly="readonly">
							</div>
						</div>
					</div>

					<div class="layui-form-item layui-row">
						<div class="layui-col-xs6">
							<label class="layui-form-label">概要</label>
							<div class="layui-input-block">
								<input type="text" class="layui-input"
									   name="overview" value="${(saleChance.overview)!}" readonly="readonly">
							</div>
						</div>
						<div class="layui-col-xs6">
							<label class="layui-form-label">成功几率(%)</label>
							<div class="layui-input-block">
								<input type="text" class="layui-input"
									   name="successRatio" value="${(saleChance.successRatio)!}"  readonly="readonly">
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

	<div class="layui-col-md12">
		<table id="cusDevPlanList" class="layui-table"  lay-filter="cusDevPlans"></table>
	</div>

	<#-- 如果开发状态是 0=未开发 和 1=开发中，则显示头部工具栏和行工具栏 -->
	<script type="text/html" id="toolbarDemo">
		<div class="layui-btn-container">
			<a class="layui-btn layui-btn-normal addNews_btn layui-bg-orange" lay-event="add">
				<i class="layui-icon">&#xe61f;</i>
				添加计划项
			</a>
			<a class="layui-btn layui-btn-normal addNews_btn layui-bg-red" lay-event="del">
				<i class="layui-icon">&#xe616</i>
				删除计划项
			</a>
			<a class="layui-btn layui-btn-normal addNews_btn layui-bg-orange" lay-event="success">
				<i class="layui-icon">&#x1005;</i>
				开发成功
			</a>
			<a class="layui-btn layui-btn-normal addNews_btn layui-bg-red" lay-event="failed">
				<i class="layui-icon">&#x1006;</i>
				开发失败
			</a>
		</div>
	</script>

	<!--操作-->
	<script id="cusDevPlanListBar" type="text/html">
		<a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>
		<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
	</script>

	<script type="text/javascript" src="js/cusDevPlan/cusDevPlanData.js"></script>
	</body>
</html>