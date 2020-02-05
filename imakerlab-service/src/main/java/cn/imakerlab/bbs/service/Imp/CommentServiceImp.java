package cn.imakerlab.bbs.service.Imp;

import cn.imakerlab.bbs.mapper.ArticleDao;
import cn.imakerlab.bbs.mapper.CommentDao;
import cn.imakerlab.bbs.mapper.UserDao;
import cn.imakerlab.bbs.model.exception.MyException;
import cn.imakerlab.bbs.model.po.Article;
import cn.imakerlab.bbs.model.po.Comment;
import cn.imakerlab.bbs.model.po.User;
import cn.imakerlab.bbs.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class CommentServiceImp implements CommentService{
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
            throw  new MyException("此用户id不存在");
        }
        return user;
    }

    @Override
    public void postCommentByUser(Integer userId, Integer articleId, String content) {
        User user = getUserById(userId);

        if (user == null) {
            throw new MyException("用户id不存在");
        }

        Comment comment = new Comment();
        comment.setArticleId(articleId);
        comment.setCommentTime(new Date());
        comment.setContent(content);
        comment.setIsDeleted(Byte.parseByte("0"));
        comment.setUserId(userId);
        comment.setUserUsername(user.getUsername());

        Integer count = commentDao.insert(comment);

        if (count == 0) {
            throw new MyException("评论失败");
        }

        log.info("用户:"+user.getUsername()+"评论成功");
    }

    @Override
    public void deleteCommentByUser(Integer userId, Integer articleId, Integer id, Integer userId2) {
        if (userId!=userId2){
            throw new MyException("您无此权限删除他人评论");
        }

        Comment comment = commentDao.selectByPrimaryKey(id);

        if (comment.getArticleId()!=articleId){
            throw new MyException("文章id与评论id不符");
        }

        Integer count = commentDao.deleteByPrimaryKey(id);

        if (count == 0) {
            throw new MyException("评论删除失败");
        }

        log.info("评论删除成功");
    }

}
