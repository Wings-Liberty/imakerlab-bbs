package cn.imakerlab.bbs.service;

import cn.imakerlab.bbs.model.po.User;

public interface CommentService {
    User getUserById(Integer userId);
    void postCommentByUser(Integer userId,String articleId,String content);
    void deleteCommentByUser(Integer userId,String articleId,Integer id,Integer userId2);

}
