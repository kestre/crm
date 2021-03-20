package org.example.crm.interceptors;

import org.example.crm.dao.UserMapper;
import org.example.crm.exceptions.NoLoginException;
import org.example.crm.utils.LoginUserUtil;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//HandlerInterceptorAdapter适配器
public class NoLoginInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);

        if(null == userId || userMapper.selectByPrimaryKey(userId) == null){
            throw new NoLoginException();
        }

        return true;
    }
}
