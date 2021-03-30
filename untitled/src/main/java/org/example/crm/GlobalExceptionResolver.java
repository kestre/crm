package org.example.crm;

import com.alibaba.fastjson.JSON;
import org.example.crm.base.ResultInfo;
import org.example.crm.exceptions.AuthException;
import org.example.crm.exceptions.NoLoginException;
import org.example.crm.exceptions.ParamsException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

        if(e instanceof NoLoginException){
            ModelAndView mv = new ModelAndView("redirect:/index");

            return mv;
        }

        ModelAndView modelAndView = new ModelAndView("error");

        modelAndView.addObject("code", 500);
        modelAndView.addObject("msg", "异常，请重试。。。");

        if (o instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) o;
            ResponseBody responseBody = handlerMethod.getMethod().getDeclaredAnnotation(ResponseBody.class);

            if (responseBody == null){
                if(e instanceof ParamsException){
                    ParamsException p = (ParamsException) e;

                    modelAndView.addObject("code", p.getCode());
                    modelAndView.addObject("msg",p.getMsg());
                }else if(e instanceof AuthException){
                    AuthException a = (AuthException) e;

                    System.out.println(a.getMsg());
                    modelAndView.addObject("code", a.getCode());
                    modelAndView.addObject("msg",a.getMsg());
                }

                return modelAndView;
            }else{

                ResultInfo resultInfo = new ResultInfo();
                resultInfo.setCode(500);
                resultInfo.setMsg("异常，请重试。。。");

                if(e instanceof ParamsException){
                    ParamsException p = (ParamsException) e;
                    resultInfo.setCode(p.getCode());
                    resultInfo.setMsg(p.getMsg());
                }else if(e instanceof AuthException){
                    AuthException a = (AuthException) e;
                    System.out.println(a.getMsg());
                    resultInfo.setCode(a.getCode());
                    resultInfo.setMsg(a.getMsg());
                }

                httpServletResponse.setContentType("application/json;charset=UTF-8");

                PrintWriter out = null;
                try{
                    out = httpServletResponse.getWriter();

                    String json = JSON.toJSONString(resultInfo);
                    out.write(json);

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } finally {
                    if(out != null){
                        out.close();
                    }
                }

                return null;
            }
        }

        return modelAndView;
    }
}
