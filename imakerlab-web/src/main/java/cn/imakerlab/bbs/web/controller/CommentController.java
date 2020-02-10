package cn.imakerlab.bbs.web.controller;

import cn.imakerlab.bbs.security.utils.SecurityUtils;
import cn.imakerlab.bbs.service.Imp.CommentServiceImp;
import cn.imakerlab.bbs.service.Imp.UserServiceImp;
import cn.imakerlab.bbs.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class CommentController {
    @Autowired
    CommentServiceImp commentServiceImp;

    @Autowired
    UserServiceImp userService;

    @ResponseBody
    @PostMapping("/comment")
    public ResultUtils postCommentByUserId(@RequestParam("articleId") Integer articleId,
                                         @RequestParam("content") String content,
                                         @RequestParam("isEffectArticle") boolean isEffectArticle,
                                         HttpServletRequest request) {

        Integer userId = SecurityUtils.getUserIdFromRequest(request);

        String username = userService.getUsernameByUserId(userId);

        commentServiceImp.insertComment(userId, username, articleId, content, isEffectArticle);

        return ResultUtils.success();
    }

    @ResponseBody
    @DeleteMapping("/comment")
    public ResultUtils deleteCommentByUser(@RequestParam("id") int id,
                                           HttpServletRequest request) {
        Integer userId = SecurityUtils.getUserIdFromRequest(request);

        commentServiceImp.deleteCommentByUserIdAndCommentId(id, userId);

        return ResultUtils.success();
    }

}
