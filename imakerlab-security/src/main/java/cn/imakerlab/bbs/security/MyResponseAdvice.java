package cn.imakerlab.bbs.security;

import cn.imakerlab.bbs.constant.DefaultConstant;
import cn.imakerlab.bbs.utils.ResultUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


/**
 * controller返回值统一处理类
 * 因为以找到其他解决方法，暂时不使用该方法
 */
//@ControllerAdvice
public class MyResponseAdvice implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {

        System.out.println("统一返回结果集拦截的是" + aClass.getName());

        System.out.println("方法参数为" + methodParameter);

        System.out.println("拦截的方法名是" + methodParameter.getMethod().getName());;

        if(methodParameter.getMethod().getName().equals(DefaultConstant.Token.GET_TOKEN_METHOD)){
            return true;
        }

        return false;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        System.out.println("原返回值为" + "\n" +
                "Object" + o + "\n" +
                "MethodParameter" + methodParameter + "\n" +
                "MediaType" + mediaType + "\n" +
                "Class" + aClass);

        ResponseEntity responseEntity = (ResponseEntity) o;

        return ResultUtils.success().setData(responseEntity.getBody());
    }
}
