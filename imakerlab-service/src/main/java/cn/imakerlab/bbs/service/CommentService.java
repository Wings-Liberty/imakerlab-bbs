package cn.imakerlab.bbs.service;

import cn.imakerlab.bbs.model.po.Comment;
import cn.imakerlab.bbs.model.po.User;
import cn.imakerlab.bbs.model.vo.CommentVo;

import java.util.List;

public interface CommentService {

    User getUserById(Integer userId);

    void insertComment(Integer userId, String username, Integer articleId, String content, boolean isEffectArticle);

    void deleteCommentByUserIdAndCommentId(Integer id, Integer userId);

    List<Comment> listCommentByArticleId(int articleId);

    /**
     * 把传来的List<Comment>变为List<CommentVo>并把所有评论者的头像信息存到List<CommentVo>中
     * 如果评论者的账号已被删除，那么list中的username和figureUrl分别设为"该用户已被删除"和一个默认的图片的url
     * @param commentList
     * @return
     */
    List<CommentVo> commentListToCommentVoList(List<Comment> commentList);

    /**
     * 用传来的articleId查询所有相关评论
     * 如果某条评论的作者用户被删除，那么该评论的作者名和头像设置为被删除用户的默认值
     * @param articleId
     * @return
     */
    List<CommentVo> listCommentVosByArticleId(int articleId);
}