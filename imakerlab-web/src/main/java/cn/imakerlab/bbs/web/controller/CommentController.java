package cn.imakerlab.bbs.web.controller;

import cn.imakerlab.bbs.security.utils.SecurityUtils;
import cn.imakerlab.bbs.service.Imp.CommentServiceImp;
import cn.imakerlab.bbs.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class CommentController {
    @Autowired
    CommentServiceImp commentServiceImp;
    @ResponseBody
    @PostMapping("/comment")
    public ResultUtils postCommentByUser(@RequestParam("userId") String userId,
                                         @RequestParam("articleId") Integer articleId,
                                         @RequestParam("content") String content){
        commentServiceImp.postCommentByUser(Integer.parseInt(userId),articleId,content);

        return ResultUtils.success();
    }

    @ResponseBody
    @DeleteMapping("/comment")
    public ResultUtils deleteCommentByUser(@RequestParam("userId") String userId,
                                           @RequestParam("articleId") Integer articleId,
                                           @RequestParam("id")String id,
                                           HttpServletRequest request){
        Integer userId2 = SecurityUtils.getUserIdFromAuthenticationByRequest(request);
        commentServiceImp.deleteCommentByUser(Integer.parseInt(userId),articleId,Integer.parseInt(id),userId2);

        return ResultUtils.success();
    }

}
