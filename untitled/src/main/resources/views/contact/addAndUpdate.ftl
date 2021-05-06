<!DOCTYPE html>
<html>
    <head>
        <#include "../common.ftl">
    </head>
    <body class="childrenBody">
        <form class="layui-form" style="width:80%;">
            <#--id隐藏域-->
            <input type="hidden" name="id" value="${(customerContact.id)!}">
            <#--cusId隐藏域-->
            <input type="hidden" id="OriginCusId" value="${(customerContact.cusId)!}">

            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">客户</label>
                <div class="layui-input-block">
                    <select name="cusId" id="cusId" lay-search>
                        <option value="">请选择</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">交往时间</label>
                <div class="layui-input-block">
<#--                    <input type="text" class="layui-input" lay-verify="required" name="contactTime" id="contactTime"  value="${(customerContact.contactTime)!}">-->
                    <input type="text" class="layui-input" lay-verify="required" name="contactTime" id="contactTime" value="${(customerContact.contactTime?string('yyyy-MM-dd'))!}">
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">交往地址</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input" name="address" value="${(customerContact.address)!}" placeholder="请输入交往地址">
                </div>
            </div>

            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">概要</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input" name="overview" value="${(customerContact.overview)!}" placeholder="请输入交往概要">
                </div>
            </div>
            <br/>
            <div class="layui-form-item layui-row layui-col-xs12">
                <div class="layui-input-block">
                    <button class="layui-btn layui-btn-lg" lay-submit=""  lay-filter="addOrUpdateLinkman">确认</button>
                    <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
                </div>
            </div>
        </form>
    <script type="text/javascript" src="js/contact/addAndUpdate.js"></script>
    </body>
</html>