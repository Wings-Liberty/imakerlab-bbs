package cn.imakerlab.bbs.web.controller;

import cn.imakerlab.bbs.model.dto.User;
import cn.imakerlab.bbs.service.Imp.TestServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @Autowired
    TestServiceImp service;

    @GetMapping("/user")
    @ResponseBody
    public User getUserList() {
        User user = service.getUser();

        return user;
    }

}