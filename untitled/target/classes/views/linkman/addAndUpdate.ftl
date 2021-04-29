<!DOCTYPE html>
<html>
    <head>
        <#include "../common.ftl">
    </head>
    <body class="childrenBody">
        <form class="layui-form" style="width:80%;">
            <#--id隐藏域-->
            <input type="hidden" name="id" value="${(customerLinkman.id)!}">
            <#--cusId隐藏域-->
            <input type="hidden" id="OriginCusId" value="${(customerLinkman.cusId)!}">
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">联系人名字</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input" lay-verify="required" name="linkName" id="linkName"  value="${(customerLinkman.linkName)!}" placeholder="请输入联系人名称">
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">性别</label>
                <div class="layui-input-block">
                    <select name="gender" id="gender">
                        <option value="">请选择</option>
                        <option value="男" <#if (customerLinkman.gender)?? && customerLinkman.gender=="男">selected="selected"</#if>>男</option>
                        <option value="女" <#if (customerLinkman.gender)?? && customerLinkman.gender=="女">selected="selected"</#if>>女</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">职位</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input" name="vocation" value="${(customerLinkman.vocation)!}" placeholder="请输入联系人职位">
                </div>
            </div>

            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">办公电话</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input" name="officePhone" value="${(customerLinkman.officePhone)!}" id="officePhone" placeholder="请输入联系电话">
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">联系电话</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input" lay-verify="required" name="phone" value="${(customerLinkman.phone)!}" id="phone" placeholder="请输入联系电话">
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">所属客户</label>
                <div class="layui-input-block">
                    <select name="cusId" id="cusId" lay-search>
                        <option value="">请选择</option>
                    </select>
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
    <script type="text/javascript" src="js/linkman/addAndUpdate.js"></script>
    </body>
</html>