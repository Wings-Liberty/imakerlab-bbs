package cn.imakerlab.bbs.web.controller;

import cn.imakerlab.bbs.mapper.UserDao;
import cn.imakerlab.bbs.model.po.User;
import cn.imakerlab.bbs.security.utils.SecurityUtils;
import cn.imakerlab.bbs.service.Imp.TestServiceImp;
import cn.imakerlab.bbs.service.Imp.UserServiceImp;
import cn.imakerlab.bbs.service.UserService;
import cn.imakerlab.bbs.utils.ResultUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class TestController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    TestServiceImp service;

    @Autowired
    UserServiceImp userService;

    @Autowired
    UserDao userDao;

    @GetMapping("/admin/test")
    @ResponseBody
    public ResultUtils admin(){
        String string = "成功访问到了管理员接口";

        System.out.println(string);

        return ResultUtils.success().setMsg(string);

    }

    @GetMapping("/me")
    @ResponseBody
    public Object getUser(Authentication user, HttpServletRequest request) throws UnsupportedEncodingException {

        //使用jwt后，返回的是字符串，会自动组装为Authentication对象，里面会有很多东西，但是没有自定义添加进去的属性

        //要想获取带有自定义属性的对象，需要使用以下语句
        String header = request.getHeader("Authorization");

        log.info("header : " + header);

        //这里的Bearer不能忽略大小写
        String token = StringUtils.substringAfter(header, "Bearer ");
        log.info("token : " + token);

        Claims claims = Jwts.parser().setSigningKey("cx".getBytes("UTF-8"))
                .parseClaimsJws(token).getBody();

        System.out.println("claims对象的实际类型是" + claims.getClass());

        System.out.println("claims : " + claims);

        System.out.println("id : " +(String) claims.get("userId"));

        System.out.println("organization : " + (String) claims.get("organization"));

        return claims;
    }

    @GetMapping("/me2")
    @ResponseBody
    public ResultUtils userList(HttpServletRequest request){

        Integer id = SecurityUtils.getUserIdFromAuthenticationByRequest(request);

        System.out.println(id);

        return ResultUtils.success().setData(id);

    }

}