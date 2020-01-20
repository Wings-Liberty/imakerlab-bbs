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
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Size;

@Controller
public class UserController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Qualifier("userServiceImp")
    @Autowired
    UserService userService;


    /**
     * @api {POST} /oauth/token 登录（获取令牌/刷新令牌）
     * @apiVersion 1.0.0
     * @apiGroup 用户
     * @apiName login
     * @apiDescription 使用用户名，密码获取令牌
     * @apiParam (请求参数) {String} grant_type 获取令牌的模式
     * @apiParam (请求参数) {String} scope 请求权限
     * @apiParam (请求参数) {String} username 用户名
     * @apiParam (请求参数) {String} password 密码
     * @apiParam (请求参数) {String} [refresh_token] 刷新令牌，刷新令牌时使用的参数，登录时不需要传这个参数
     * @apiParamExample 获取令牌传参示例（application/x-www-form-urlencoded）
     * {
     *     "grant_type":"password",
     *     "username":"tom",
     *     "password":"111",
     *     "scope":"all"
     * }
     * @apiParamExample 刷新令牌传参示例（application/x-www-form-urlencoded）
     * {
     *     "grant_type":"refresh_token",
     *     "refresh_token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NzkzMjgwNDQsInVzZXJfbmFtZSI6ImFhYSIsImp0aSI6IjgzMmExMjhmLWEwMjEtNGJjNS04YTA4LWJhNGNiN2I5YzQ2YSIsImNsaWVudF9pZCI6ImltYWtlciIsInNjb3BlIjpbImFsbCJdLCJhdGkiOiIwYzA2NjcyMy1kMDc2LTQ2NzQtOWZmOS1lNTNkZDg4NTA3YTkifQ.w4s_rkI2cqhiiwFQWwug4eQxwioEyxNLaWuKk1IuI44"
     * }
     * @apiSuccess (响应结果) {Number} code 状态码
     * @apiSuccess (响应结果) {String} msg 返回的字符串信息
     * @apiSuccess (响应结果) {Object} data 返回的对象
     * @apiSuccess (响应结果) {String} access_token 令牌
     * @apiSuccess (响应结果) {String} token_type 令牌类型
     * @apiSuccess (响应结果) {String} refresh_token 刷新令牌
     * @apiSuccess (响应结果) {Number} expires_in 令牌剩余有效时间
     * @apiSuccess (响应结果) {String} scope 请求的权限
     * @apiSuccessExample 成功响应结果示例
     * {
     *     "code": 200,
     *     "msg": "成功",
     *     "data": {
     *         "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NzkyNDE3MDQsInVzZXJfbmFtZSI6ImFhYSIsImp0aSI6IjBjMDY2NzIzLWQwNzYtNDY3NC05ZmY5LWU1M2RkODg1MDdhOSIsImNsaWVudF9pZCI6ImltYWtlciIsInNjb3BlIjpbImFsbCJdfQ.kmrFJEiOWOEWlOrVtOslLUj71Cgeg5IcuFN94jQ7URQ",
     *         "token_type": "bearer",
     *         "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NzkzMjgwNDQsInVzZXJfbmFtZSI6ImFhYSIsImp0aSI6IjgzMmExMjhmLWEwMjEtNGJjNS04YTA4LWJhNGNiN2I5YzQ2YSIsImNsaWVudF9pZCI6ImltYWtlciIsInNjb3BlIjpbImFsbCJdLCJhdGkiOiIwYzA2NjcyMy1kMDc2LTQ2NzQtOWZmOS1lNTNkZDg4NTA3YTkifQ.w4s_rkI2cqhiiwFQWwug4eQxwioEyxNLaWuKk1IuI44",
     *         "expires_in": 58,
     *         "scope": "all",
     *         "organization": "imaker"
     *     }
     * }
     * @apiErrorExample 用户名或密码错误响应结果示例
     * {
     *     "code": 100,
     *     "msg": "用户名或密码错误",
     *     "data": ""
     * }
     * @apiErrorExample 刷新令牌失败响应结果示例
     * {
     *     "code": 100,
     *     "msg": "令牌已过期",
     *     "data": ""
     *}
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
     * {"password":"","figureUrl":"/figuer/user1","id":4910,"slogan":"我是个性签名","username":"我是用户名"}
     * @apiErrorExample 令牌过期响应结果示例
     * {
     *     "code": 100,
     *     "msg": "令牌已过期",
     *     "data": ""
     * }
     */
    @GetMapping("/user")
    @ResponseBody
    public ResultUtils getUser(Authentication authentication) {

        if (authentication == null) {
            throw new MyException("请先登录");
        }

        String username = authentication.getName();

        if (UsersUtils.usersMap.containsKey(username)) {
            logger.info("从userMap中获取user");
            return ResultUtils.success().setData(UsersUtils.usersMap.get(username));
        } else {
            logger.info("从数据库获取user，并将该user存入UserUtils的usersMap");
            User user = userService.getUserByAuthentication(authentication);
            UsersUtils.usersMap.put(username, user);
            return ResultUtils.success().setData(user);
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
     * @apiSuccessExample 成功响应结果示例
     * {"msg":"成功","code":200,"body":{}}
     * @apiErrorExample 图片大小超出限制响应结果示例
     * {
     *     "code": 100,
     *     "msg": "文件大小超出限制",
     *     "data": ""
     * }
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