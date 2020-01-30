package cn.imakerlab.bbs.security.utils;

import cn.imakerlab.bbs.model.exception.MyException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

public class SecurityUtils {

    public static int getUserIdFromAuthenticationByRequest(HttpServletRequest request){

        String header = request.getHeader("Authorization");

        //这里的Bearer不能忽略大小写
        String token = StringUtils.substringAfter(header, "Bearer ");

        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey("cx".getBytes("UTF-8"))
                    .parseClaimsJws(token).getBody();
        } catch (UnsupportedEncodingException e) {
            throw new MyException("security模块的工具类抛出的UnsupportedEncodingException");
        }

        System.out.println("claims : " + claims);

        Integer userId = (Integer) claims.get("userId");

        return userId;
    }

}
