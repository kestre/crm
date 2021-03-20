layui.use(['form','jquery'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery;

    // 表单提交
    form.on('submit(login)', function(data){
        // console.log(data.field)
        $.ajax({
            type:'POST',
            url:crm + "/user/login",
            data:{
                userName:data.field.username,
                userPwd:data.field.password
            },
            success:function (result){
                if(result.code == 200){
                    layer.msg("登录成功！",function (){
                        if($("#rememberMe").prop("checked")){
                            $.cookie("userId",result.result.userIdStr, {expires:7});
                            $.cookie("userName",result.result.userName, {expires:7});
                            $.cookie("trueName",result.result.trueName, {expires:7});
                        }else{
                            $.cookie("userId",result.result.userIdStr);
                            $.cookie("userName",result.result.userName);
                            $.cookie("trueName",result.result.trueName);
                        }

                        window.location.href = crm + "/main"});
                }else{
                    layer.msg(result.msg,{icon:5})
                }
            }
        })
        return false;
    });
});