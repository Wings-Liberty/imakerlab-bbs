package cn.imakerlab.bbs.web.controller;

import cn.imakerlab.bbs.model.dto.User;
import cn.imakerlab.bbs.service.Imp.TestServiceImp;
import cn.imakerlab.bbs.utils.ResultUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Date;

@Controller
public class TestController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    TestServiceImp service;

    @GetMapping("/getStr")
    @ResponseBody
    public ResultUtils getStr(String str, Authentication authentication){

        System.out.println(str);



        return ResultUtils.success();
        
    }

    @GetMapping("/authen")
    @ResponseBody
    public Authentication getAuth(Authentication authentication, HttpServletRequest request){

        return authentication;

    }

    @GetMapping("/me")
    @ResponseBody
    public Object getUser(Authentication user, HttpServletRequest request) throws UnsupportedEncodingException {
        //使用jwt后，返回的是字符串，会自动组装为Authentication对象，里面会有很多东西，但是没有自定义添加进去的属性

        //要想获取带有自定义属性的对象，需要使用以下语句
        String header = request.getHeader("Authorization");

        logger.info("header : " + header);

        //这里的Bearer不能忽略大小写
        String token = StringUtils.substringAfter(header, "Bearer ");

        logger.info("token : " + token);

        Claims claims = Jwts.parser().setSigningKey("cx".getBytes("UTF-8"))
                .parseClaimsJws(token).getBody();

        System.out.println("claims : " + claims);

        System.out.println(claims.get("user_name"));

        return claims;
    }

}