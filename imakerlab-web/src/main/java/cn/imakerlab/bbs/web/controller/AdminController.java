package cn.imakerlab.bbs.web.controller;

import cn.imakerlab.bbs.model.exception.MyException;
import cn.imakerlab.bbs.model.po.User;
import cn.imakerlab.bbs.model.vo.UserVo;
import cn.imakerlab.bbs.service.UserService;
import cn.imakerlab.bbs.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    UserService userService;

    @GetMapping("/admin/users")
    @ResponseBody
    public ResultUtils getAllNotDeletedUser(@RequestParam(required = false, defaultValue = "1") int pn){

        logger.info("进接口里了");
        System.out.println(pn);
        List<User> userList = userService.getAllNotDeletedUser();

        List<UserVo> userVoList = new ArrayList<>();

        for(User user : userList){
            userVoList.add(new UserVo(user));
        }

        if(CollectionUtils.isEmpty(userVoList)){
            throw new MyException("获取到的用户列表为空");
        }

//        PageHelper.startPage(pn, DefaultConstant.Page.PAGE_SIZE);
//        PageInfo<UserVo> pageInfo = new PageInfo<>(userVoList, DefaultConstant.Page.NAVAGATE_PAGES);
//
//        return ResultUtils.success().setData(pageInfo);

        return ResultUtils.success();

    }



}