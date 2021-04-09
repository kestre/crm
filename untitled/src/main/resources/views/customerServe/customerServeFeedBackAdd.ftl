<!DOCTYPE html>
<head>
	<#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
	<input name="id" type="hidden" value="${customerServe.id}">
	<input id="id" name="state" type="hidden" value="fw_004"/>
	<#--assignMan隐藏域-->
	<input type="hidden" id="otherAssignMan" value="${(customerServe.assigner)!}">

	<div class="layui-form-item layui-row">
		<div class="layui-col-xs6">
			<label class="layui-form-label">服务类型</label>
			<div class="layui-input-block">
				<select name="serveType" id="serveType" disabled="disabled">
					<option value="" >请选择</option>
					<option value="6" <#if customerServe.serveType=="6">selected="selected"</#if>>咨询</option>
					<option value="7" <#if customerServe.serveType=="7">selected="selected"</#if>>投诉</option>
					<option value="8" <#if customerServe.serveType=="8">selected="selected"</#if>>建议</option>
				</select>
			</div>
		</div>
		<div class="layui-col-xs6">
			<label class="layui-form-label">客户</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input" lay-verify="required" name="customer" id="customer" value="${(customerServe.customer)!}" readonly="readonly">
			</div>
		</div>
	</div>

	<div class="layui-form-item layui-row">
		<label class="layui-form-label">服务内容</label>
		<div class="layui-input-block">
			<textarea class="layui-textarea" lay-verify="required" name="serviceRequest" readonly="readonly">${(customerServe.serviceRequest)!}</textarea>
		</div>
	</div>
	<div class="layui-form-item layui-row">
		<label class="layui-form-label">服务概要</label>
		<div class="layui-input-block">
			<textarea class="layui-textarea" lay-verify="required" name="overview" readonly="readonly">${(customerServe.overview)!}</textarea>
		</div>
	</div>

	<div class="layui-form-item layui-row">
		<div class="layui-col-xs6">
			<label class="layui-form-label">指派人</label>
			<div class="layui-input-block">
				<select name="assigner" id="assigner" disabled="disabled">
					<option value="" >请选择</option>
				</select>
			</div>
		</div>
		<div class="layui-col-xs6">
			<label class="layui-form-label">指派时间</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input" lay-verify="required" name="assignTime" id="assignTime" value="${(customerServe.assignTime?string('yyyy-MM-dd HH:mm:ss'))!}" readonly="readonly">
			</div>
		</div>
	</div>

	<div class="layui-form-item layui-row">
		<div class="layui-col-xs6">
			<label class="layui-form-label">处理内容</label>
			<div class="layui-input-block">
				<textarea name="serviceProce" id="serviceProce" class="layui-textarea" disabled="disabled">${(customerServe.serviceProce)!}</textarea>
			</div>
		</div>
		<div class="layui-col-xs6">
			<label class="layui-form-label">处理时间</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input" lay-verify="required" name="serviceProceTime" id="serviceProceTime" value="${(customerServe.serviceProceTime?string('yyyy-MM-dd HH:mm:ss'))!}" readonly="readonly">
			</div>
		</div>
	</div>

	<div class="layui-form-item layui-row">
		<div class="layui-col-xs6">
			<label class="layui-form-label">处理结果</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input"
					   name="serviceProceResult" id="serviceProceResult" class="layui-textarea"/>
			</div>
		</div>
		<div class="layui-col-xs6">
			<label class="layui-form-label">满意度</label>
			<div class="layui-input-block">
				<select name="satisfied" id="satisfied">
					<option value="" >请选择</option>
					<option value="*">*</option>
					<option value="**">**</option>
					<option value="***">***</option>
					<option value="****">****</option>
					<option value="*****">*****</option>
				</select>
			</div>
		</div>
	</div>

	</br>
	<div class="layui-form-item layui-row layui-col-xs12">
		<div class="layui-input-block">
			<button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="addOrUpdateCustomerServe">确认</button>
			<button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
		</div>
	</div>


</form>

<script type="text/javascript" src="js/customerServe/customerServeFeedBackAdd.js"></script>
</body>
</html>