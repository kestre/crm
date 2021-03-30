package org.example.crm.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.example.crm.annoation.RequiredPermission;
import org.example.crm.exceptions.AuthException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Component
@Aspect
public class PermissionProxy {

    @Resource
    private HttpSession session;

    @Around(value = "@annotation(org.example.crm.annoation.RequiredPermission)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Object result = null;

        List<String> permissions = (List<String>) session.getAttribute("permissions");

        if(null == permissions || permissions.size() < 1) {
            throw new AuthException();
        }

        // 得到对应的目标
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        // 得到方法上的注解
        RequiredPermission requiredPermission = methodSignature.getMethod().getDeclaredAnnotation(RequiredPermission.class);
        // 判断注解上对应的状态码
        if (!(permissions.contains(requiredPermission.code()))) {

            throw new AuthException();
        }

        result = pjp.proceed();
        return result;
    }
}
