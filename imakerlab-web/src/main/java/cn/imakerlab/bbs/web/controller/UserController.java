package cn.imakerlab.bbs.web.controller;

import cn.imakerlab.bbs.model.dto.User;
import cn.imakerlab.bbs.model.exception.MyException;
import cn.imakerlab.bbs.model.vo.UserVo;
import cn.imakerlab.bbs.service.UserService;
import cn.imakerlab.bbs.utils.MyUtils;
import cn.imakerlab.bbs.utils.ResultUtils;
import cn.imakerlab.bbs.utils.UsersUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class UserController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Qualifier("userServiceImp")
    @Autowired
    UserService userService;


    /**
     * @api {POST} /oauth/token 登陆
     * @apiVersion 1.0.0
     * @apiGroup 用户
     * @apiName login
     * @apiDescription 使用用户名，密码获取令牌
     * @apiParam (请求参数) {String} grant_type 获取令牌的模式
     * @apiParam (请求参数) {String} scope 请求权限
     * @apiParam (请求参数) {String} username 用户名
     * @apiParam (请求参数) {String} password 密码
     * @apiParam (请求参数) {String} [refresh_token] 刷新令牌
     * @apiParamExample application/x-www-form-urlencoded传参示例
     * {
     *     "grant_type":"password",
     *     "username":"tom",
     *     "password":"111",
     *     "scope":"all"
     * }
     * @apiParamExample application/x-www-form-urlencoded传参示例
     * {
     *     "grant_type":"refresh_token",
     *     "username":"tom",
     *     "password":"111",
     *     "scope":"all"
     * }
     * @apiSuccess (响应结果) {String} access_token 令牌
     * @apiSuccess (响应结果) {Number} expires_in 令牌过期时间
     * @apiSuccess (响应结果) {String} token_type 令牌类型
     * @apiSuccess (响应结果) {String} refresh_token 刷新令牌
     * @apiSuccess (响应结果) {Object} body 返回的对象
     * @apiSuccessExample 成功响应结果示例
     * {
     *     "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1Nzg5ODA5MjksInVzZXJfbmFtZSI6ImFhYSIsImp0aSI6IjM3NjAxM2IyLWFkY2YtNGYxMC1hMDhhLTNkODZkNWJiZDhkOCIsImNsaWVudF9pZCI6ImltYWtlciIsInNjb3BlIjpbImFsbCJdfQ.GSEvaK-X4PHHXMPhs6MP3yiUkum1urv-ObU9980MwhE",
     *     "token_type": "bearer",
     *     "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NzkwNjM3MjksInVzZXJfbmFtZSI6ImFhYSIsImp0aSI6IjkzYmRhOWJiLTJiYWQtNDJkMi04MDQyLWNjZTViMWIyOGFhMSIsImNsaWVudF9pZCI6ImltYWtlciIsInNjb3BlIjpbImFsbCJdLCJhdGkiOiIzNzYwMTNiMi1hZGNmLTRmMTAtYTA4YS0zZDg2ZDViYmQ4ZDgifQ.Hyf40vSumo8R1NpLBRcG0shNZTt_ZMeLMtpSmf-OBaA",
     *     "expires_in": 3599,
     *     "scope": "all",
     *     "organization": "imaker"
     * }
     * @apiError (响应结果) {String} error 错误类型
     * @apiError (响应结果) {String} error_description 错误提示信息
     * @apiErrorExample 用户名或密码错误响应结果示例
     * {
     *     "error": "invalid_grant",
     *     "error_description": "用户名或密码错误"
     * }
     * @apiErrorExample 刷新令牌失败响应结果示例
     * {
     *     "error": "invalid_grant",
     *     "error_description": "Invalid refresh token: 1312412435135"
     * }
     */

    /**
     * @api {POST} /register 注册
     * @apiVersion 1.0.0
     * @apiGroup 用户
     * @apiName register
     * @apiDescription 输入用户名，密码进行注册。用户名不能与数据库里的已有有户名重复
     * @apiParam (请求参数) {String} username 用户名
     * @apiParam (请求参数) {String} password 密码
     * @apiSuccess (响应结果) {Number} code 状态码
     * @apiSuccess (响应结果) {String} msg 返回的字符串信息
     * @apiSuccess (响应结果) {Object} body 返回的对象
     * @apiSuccessExample 成功响应结果示例
     * {"msg":"成功","code":200,"body":{}}
     * @apiErrorExample 用户名重复响应结果示例
     * {"msg":"用户名已存在","code":100,"body":{}}
     * @apiErrorExample 用户名格式不合理响应结果示例
     * {"msg":"用户名长度不合理，规定用户名长度为2~15","code":100,"body":{}}
     */
    @PostMapping("/register")
    @ResponseBody
    public ResultUtils register(@Valid UserVo userVo) {

        userService.register(userVo);

        return ResultUtils.success();
    }

    /**
     * @api {GET} /user 获取用户信息
     * @apiHeader {String} Bearer 令牌
     * @apiVersion 1.0.0
     * @apiGroup 用户
     * @apiName getUser
     * @apiDescription 获取用户信息。因为现在user表里信息很少，所以接口暂时把获取用户公开信息和获取用户私密信息的接口和位一个接口
     * @apiSuccess (响应结果) {Number} id 用户id
     * @apiSuccess (响应结果) {String} username 用户名
     * @apiSuccess (响应结果) {String} password 密码。这里获取的密码是不可见的
     * @apiSuccess (响应结果) {String} figureUrl 头像的url，图片存后端文件夹里
     * @apiSuccess (响应结果) {String} slogan 标语/个性签名
     * @apiSuccessExample 成功响应结果示例
     * {"password":"","figureUrl":"/figuer","id":4910,"slogan":"我是个性签名","username":"我是用户名"}
     * @apiErrorExample jwt过期响应结果示例
     * {
     *     "error": "invalid_token",
     *     "error_description": "Access token expired: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1Nzg5OTU2NTIsInVzZXJfbmFtZSI6ImJiYiIsImp0aSI6Ijc1ZGZhOTkwLTRjODAtNGU2OC1hY2Y2LWE0ZDg4ZTQxZjgwYyIsImNsaWVudF9pZCI6ImltYWtlciIsInNjb3BlIjpbImFsbCJdfQ.jFJIQFnO9cJcdKM9Cv9_l8YGSj0IAqVUYsn5A1Yy94E"
     * }
     */
    @GetMapping("/user")
    @ResponseBody
    public User getUser(Authentication authentication) {

        if (authentication == null) {
            throw new MyException("请先登录");
        }

        String username = authentication.getName();

        if (UsersUtils.usersMap.containsKey(username)) {
            logger.info("从userMap中获取user");
            return UsersUtils.usersMap.get(username);
        } else {
            logger.info("从数据库获取user");
            User user = userService.getUserByAuthentication(authentication);
            UsersUtils.usersMap.put(username, user);
            return user;
        }
    }

    /**
     * @api {PUT} /figure 上传头像
     * @apiVersion 1.0.0
     * @apiGroup 用户
     * @apiName uploadFigure
     * @apiDescription 上传头像图片文件，暂定图片大小不能超过3MB
     * @apiParam (请求参数) {Object} file 上传的头像图片文件
     * @apiParamExample 请求参数示例
     * file=
     * @apiSuccess (响应结果) {Number} code 状态码
     * @apiSuccess (响应结果) {String} msg 返回的字符串信息
     * @apiSuccess (响应结果) {Object} body 返回的对象
     * @apiSuccessExample 响应结果示例
     * {"msg":"成功","code":200,"body":{}}
     */
    @PutMapping("/figure")
    @ResponseBody
    public ResultUtils uploadFigure(@RequestParam(required = true) MultipartFile file,
                                    HttpServletRequest request,
                                    Authentication authentication) {

        //上传头像
        String figureUrl = MyUtils.uplode(file);
        logger.info("用户:"+authentication.getName() + "把头像保存在" +figureUrl);

        //把头像的url存入数据库
        userService.setFigureUrl(figureUrl, authentication.getName());

        return ResultUtils.success();
    }

    /**
     * @api {PUT} /slogan 修改个性签名
     * @apiVersion 1.0.0
     * @apiGroup 用户
     * @apiName setSlogan
     * @apiDescription 修改个性签名
     * @apiParam (请求参数) {String} slogan
     * @apiParamExample 请求参数示例
     * slogan=我是个性签名
     * @apiSuccess (响应结果) {Number} code 状态码
     * @apiSuccess (响应结果) {String} msg 返回的字符串信息
     * @apiSuccess (响应结果) {Object} body 返回的对象
     * @apiSuccessExample 响应结果示例
     * {"msg":"成功","code":200,"body":{}}
     */
    @PutMapping("/slogan")
    @ResponseBody
    public ResultUtils setSlogan(@RequestParam(required = true) String slogan,
                                    Authentication authentication){

        userService.setSlogan(slogan, authentication.getName());

        return ResultUtils.success();
    }

}