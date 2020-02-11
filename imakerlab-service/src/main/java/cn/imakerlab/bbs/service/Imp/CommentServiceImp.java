package cn.imakerlab.bbs.service.Imp;

import cn.imakerlab.bbs.constant.DefaultConsts;
import cn.imakerlab.bbs.constant.ErrorConsts;
import cn.imakerlab.bbs.mapper.ArticleDao;
import cn.imakerlab.bbs.mapper.CommentDao;
import cn.imakerlab.bbs.mapper.UserDao;
import cn.imakerlab.bbs.model.exception.MyException;
import cn.imakerlab.bbs.model.po.Comment;
import cn.imakerlab.bbs.model.po.CommentExample;
import cn.imakerlab.bbs.model.po.User;
import cn.imakerlab.bbs.model.vo.CommentVo;
import cn.imakerlab.bbs.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class CommentServiceImp implements CommentService {
    @Autowired
    CommentDao commentDao;

    @Autowired
    UserDao userDao;

    @Autowired
    ArticleDao articleDao;

    @Override
    public User getUserById(Integer userId) {
        User user = userDao.selectByPrimaryKey(userId);
        if (user == null) {
            throw new MyException("此用户id不存在");
        }
        return user;
    }

    @Override
    public void insertComment(Integer userId, String username, Integer articleId, String content, boolean isEffectArticle) {

        if (content.length() > DefaultConsts.Comment.COMMENT_MAX_LENGTH) {
            throw new MyException(ErrorConsts.Comment.COMMENT_LENGTH_IS_EXECEEDS);
        }

        byte effectArticle = (byte) (isEffectArticle ? 1 : 0);

        Comment comment = new Comment();
        comment.setArticleId(articleId);
        comment.setCommentTime(new Date());
        comment.setContent(content);
        comment.setIsDeleted((byte) 0);
        comment.setUserId(userId);
        comment.setUserUsername(username);
        comment.setIsEffectArticle(effectArticle);

        commentDao.insert(comment);

    }

    @Override
    public void deleteCommentByUserIdAndCommentId(Integer id, Integer userId) {

        Comment comment = new Comment();
        comment.setId(id);
        comment.setUserId(userId);
        comment.setIsDeleted((byte) 1);

        commentDao.updateByPrimaryKeySelective(comment);

        log.info("评论删除成功");
    }

    @Override
    public List<Comment> listCommentByArticleId(int articleId) {

        CommentExample example = new CommentExample();
        example.createCriteria()
                .andArticleIdEqualTo(articleId);

        List<Comment> commentList = commentDao.selectByExample(example);

        return commentList;

    }

    @Override
    public List<CommentVo> commentListToCommentVoList(List<Comment> commentList) {

        List<CommentVo> commentVoList = new ArrayList<>();

        for (Comment comment : commentList) {

            User user = userDao.selectByPrimaryKey(comment.getUserId());

            if (user == null) {
                log.info("评论列表中有不存在的用户的id，id为 " + comment.getUserId());
                continue;
            }
            if (user.getIsDeleted() == 1) {
                user.setUsername(DefaultConsts.User.DELETED_USER_USERNAME);
                user.setFigureUrl(DefaultConsts.User.DELETED_USER_FIGURE_URL);
            }

            commentVoList.add(new CommentVo(
                    comment,
                    user.getUsername(),
                    user.getFigureUrl()
            ));
        }

        return commentVoList;
    }

    @Override
    public List<CommentVo> listCommentVosByArticleId(int articleId) {

        List<Comment> commentList = listCommentByArticleId(articleId);

        List<CommentVo> commentVoList = commentListToCommentVoList(commentList);

        return commentVoList;

    }

}