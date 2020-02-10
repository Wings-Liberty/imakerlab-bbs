package cn.imakerlab.bbs.web.controller;

import cn.imakerlab.bbs.model.po.ContributionMap;
import cn.imakerlab.bbs.security.utils.SecurityUtils;
import cn.imakerlab.bbs.service.Imp.ContributionMapServiceImp;
import cn.imakerlab.bbs.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ContributionMapController {

    @Autowired
    ContributionMapServiceImp contributionMapService;

    @GetMapping({"/calendar/{userIdStr}", "/calendar"})
    @ResponseBody
    public ResultUtils listCalendarsByUserId(@PathVariable(required = false) String userIdStr,
                                           HttpServletRequest request) {

        Integer userId;

        if (StringUtils.isEmpty(userIdStr)) {
            userId = SecurityUtils.getUserIdFromRequest(request);
        } else {
            userId = Integer.parseInt(userIdStr);
        }

        List<ContributionMap> list = contributionMapService.listCalendarsByUserId(userId);

        Map<String, List<ContributionMap>> map = new HashMap<>();
        map.put("set", list);

        return ResultUtils.success(map);
    }

}