package cn.imakerlab.bbs.web.controller;

import cn.imakerlab.bbs.constant.DefaultConstant;
import cn.imakerlab.bbs.model.exception.MyException;
import cn.imakerlab.bbs.model.po.User;
import cn.imakerlab.bbs.model.vo.UserVo;
import cn.imakerlab.bbs.service.Imp.UserServiceImp;
import cn.imakerlab.bbs.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class AdminController {

    @Autowired
    UserServiceImp userService;

    @GetMapping("/admin/users")
    @ResponseBody
    public ResultUtils getAllNotDeletedUser(@RequestParam(required = false, defaultValue = "1") int pn){

        PageHelper.startPage(pn, DefaultConstant.Page.PAGE_SIZE);

        List<User> userList = userService.getAllNotDeletedUser();

        List<UserVo> userVoList = new ArrayList<>();

        for(User user : userList){
            userVoList.add(new UserVo(user));
        }

        if(CollectionUtils.isEmpty(userVoList)){
            throw new MyException("获取到的用户列表为空");
        }

        PageInfo<UserVo> pageInfo = new PageInfo<>(userVoList, DefaultConstant.Page.NAVAGATE_PAGES);

        return ResultUtils.success().setData(pageInfo);

    }

    @DeleteMapping("/admin/users")
    @ResponseBody
    public ResultUtils deleteUser(@RequestParam(required = true) List<Integer> delList){

        if(CollectionUtils.isEmpty(delList)){
            throw new MyException("delList是空的");
        }

        userService.deleteUserByList(delList);

        return ResultUtils.success();
    }

}