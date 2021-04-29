<!DOCTYPE html>
<html>
    <head>
        <#include "../common.ftl">
    </head>
    <body class="childrenBody">
        <form class="layui-form" style="width:80%;">
            <#-- 隐藏域 -->
            <input name="id" type="hidden" value="${(userModel.id)!}"/>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">用户名</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input" readonly="readonly"
                           lay-verify="required" name="userName" id="userName"  value="${(userModel.userName)!}" placeholder="请输入用户名">
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">真实姓名</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input" readonly="readonly"
                           lay-verify="required" name="trueName" id="trueName" value="${(userModel.trueName)!}" placeholder="请输入真实姓名">
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">邮箱</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input" readonly="readonly"
                           lay-verify="email" name="email" value="${(userModel.email)!}"
                           id="email"
                           placeholder="请输入邮箱">
                </div>
            </div>

            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">手机号</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input" readonly="readonly"
                           lay-verify="phone" name="phone" value="${(userModel.phone)!}" id="phone" placeholder="请输入手机号">
                </div>
            </div>

            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">角色</label>
                <div class="layui-input-block">
                    <select name="roleIds" lay-verify="required" xm-select="selectId" disabled="disabled"></select>
                </div>
            </div>

            <br/>
            <div class="layui-form-item layui-row layui-col-xs12">
                <div class="layui-input-block" style="display: none" id="edit-btn">
                    <button class="layui-btn layui-btn-lg" lay-submit=""
                            lay-filter="addOrUpdateUser">确认
                    </button>
                    <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
                </div>
                <div class="layui-input-block" id="cancel-btn">
                <button class="layui-btn layui-btn-lg layui-btn-normal" id="edit">修改</button>
                </div>
            </div>
        </form>

    <script type="text/javascript" src="js/user/setting.js"></script>
    </body>
</html>