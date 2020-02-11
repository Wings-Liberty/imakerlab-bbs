package cn.imakerlab.bbs.web.controller;

import cn.imakerlab.bbs.constant.DefaultConsts;
import cn.imakerlab.bbs.model.vo.UserVo;
import cn.imakerlab.bbs.security.utils.SecurityUtils;
import cn.imakerlab.bbs.service.Imp.UserServiceImp;
import cn.imakerlab.bbs.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Controller
public class AdminController {

    @Autowired
    UserServiceImp userService;

    @GetMapping("/admin/users")
    @ResponseBody
    public ResultUtils getAllNotDeletedUser(@RequestParam(required = false, defaultValue = "1")
                                                    Integer pn,
                                            HttpServletRequest request) {

        PageHelper.startPage(pn, DefaultConsts.Page.PAGE_SIZE);

        String authority = SecurityUtils.getUserAuthorityFromRequest(request);

        List<UserVo> userVoList = userService.listAllNotDeletedUserVos(authority);

        PageInfo<UserVo> pageInfo = new PageInfo<>(userVoList, DefaultConsts.Page.NAVAGATE_PAGES);

        return ResultUtils.success(pageInfo);

    }

    @DeleteMapping("/admin/users")
    @ResponseBody
    public ResultUtils deleteUser(@RequestBody List<Integer> delList,
                                  HttpServletRequest request) {

        String authority = SecurityUtils.getUserAuthorityFromRequest(request);

        userService.deleteUserByList(delList, authority);

        return ResultUtils.success();
    }

}