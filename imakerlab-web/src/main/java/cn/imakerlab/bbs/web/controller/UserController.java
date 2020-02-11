package cn.imakerlab.bbs.web.controller;

import cn.imakerlab.bbs.enums.FileUploadEnum;
import cn.imakerlab.bbs.model.vo.UserVo;
import cn.imakerlab.bbs.security.utils.SecurityUtils;
import cn.imakerlab.bbs.service.Imp.UserServiceImp;
import cn.imakerlab.bbs.utils.MyUtils;
import cn.imakerlab.bbs.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
public class UserController {

    @Autowired
    UserServiceImp userService;

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
    public ResultUtils register(@RequestParam String username,
                                @RequestParam String password) {

        userService.register(username, password);
        log.info("注册成功");

        return ResultUtils.success();
    }

    /**
     * 通过id获取单个用户信息，如果不传id，就获取当前登录用户的用户信息
     *
     * @param idStr   用户id
     * @param request
     * @return
     */
    @GetMapping({"/user/{idStr}", "/user"})
    @ResponseBody
    public ResultUtils getUserById(@PathVariable(required = false) String idStr,
                                   HttpServletRequest request) {

        int userId;

        if (StringUtils.isEmpty(idStr)) {
            userId = SecurityUtils.getUserIdFromRequest(request);
            log.info("查询（id=" + userId + "）别人的信息");
        } else {
            userId = Integer.parseInt(idStr);
            log.info("查询（id=" + userId + "）当前登录用户的信息");
        }

        UserVo userVo = userService.getNotDeleteedUserVoById(userId);

        return ResultUtils.success(userVo);

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
     * "code": 100,
     * "msg": "文件大小超出限制",
     * "data": ""
     * }
     */
    /**
     * 上传头像
     * 接收的图片有大小限制，但暂时没有检查文件是不是图片文件
     *
     * @param file
     * @param request
     * @return
     */
    @PostMapping("/figure")
    @ResponseBody
    public ResultUtils uploadFigure(MultipartFile file,
                                    HttpServletRequest request) {

        int userId = SecurityUtils.getUserIdFromRequest(request);

        //上传头像
        String figureUrl = MyUtils.uplode(file, FileUploadEnum.FIGURE);

        log.info("id为" + userId + "的用户:" + "把头像保存在" + figureUrl);

        //把头像的url存入数据库
        userService.setFigureUrl(figureUrl, userId);

        return ResultUtils.success();
    }

    /**
     * @api {PUT} /slogan 修改个性签名和用户名
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
    /**
     * 修改用户名和个签
     *
     * @param newSlogan
     * @param newUsername
     * @param request
     * @return
     */
    @PostMapping("/user")
    @ResponseBody
    public ResultUtils setSloganOrUsername(
            @RequestParam String newSlogan,
            @RequestParam String newUsername,
            HttpServletRequest request) {

        int userId = SecurityUtils.getUserIdFromRequest(request);

        /*
         检查新的用户名格式是否符合正则表达式
         检查个签长度
         */

        userService.setSloganAndUsernameByUserId(userId, newSlogan, newUsername);

        return ResultUtils.success();
    }


    /**
     * 修改密码
     *
     * @param oldPassword
     * @param newPassword
     * @param request
     * @return
     */
    @PutMapping("/user/password")
    @ResponseBody
    public ResultUtils modifyPassword(@RequestParam String oldPassword,
                                      @RequestParam String newPassword,
                                      HttpServletRequest request) {

        int userId = SecurityUtils.getUserIdFromRequest(request);

        userService.updatePasswordByUserId(userId, oldPassword, newPassword);

        return ResultUtils.success();

    }

}