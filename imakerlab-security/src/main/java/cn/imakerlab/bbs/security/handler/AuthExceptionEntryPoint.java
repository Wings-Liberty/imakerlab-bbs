package cn.imakerlab.bbs.security.handler;

import cn.imakerlab.bbs.constant.ErrorConstant;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * toekn校验失败（token过期或不匹配）处理类
 *
 */
public class AuthExceptionEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException)
            throws ServletException {

        Map<String,String> map = new HashMap<>();
        map.put("code", "100");

        if(authException.getMessage().contains(ErrorConstant.Token.TOKEN_IS_EXPIRED)){
            map.put("msg", "token已过期");

        }else {
            map.put("msg", authException.getMessage());
        }

        map.put("body" , "");

        response.setContentType("application/json");
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getOutputStream(), map);
        } catch (Exception e) {
            throw new ServletException();
        }
    }
}