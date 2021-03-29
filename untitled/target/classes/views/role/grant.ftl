<!DOCTYPE html>
<html>
<head>
    <title>角色授权</title>
    <#--添加base标签-->
    <base href="${crm}/"/>
    <#include "../common.ftl">
    <link rel="stylesheet" href="css/zTreeStyle.css">
    <script src="lib/zTree/jquery.ztree.core.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="lib/zTree/jquery.ztree.excheck.min.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>

    <input type="hidden" name="roleId" value="${roleId!}">

    <div id="zTree" class="ztree"></div>

<script type="text/javascript" src="js/role/grant.js"></script>
</body>
</html>