package cn.imakerlab.bbs.service.Imp;

import cn.imakerlab.bbs.constant.ErrorConstant;
import cn.imakerlab.bbs.mapper.ArticleDao;
import cn.imakerlab.bbs.mapper.CommentDao;
import cn.imakerlab.bbs.mapper.UserDao;
import cn.imakerlab.bbs.model.exception.MyException;
import cn.imakerlab.bbs.model.po.*;
import cn.imakerlab.bbs.model.vo.ArticleVo;

import cn.imakerlab.bbs.model.vo.CommentVo;
import cn.imakerlab.bbs.model.vo.UserAndArticleAndCommentsVo;
import cn.imakerlab.bbs.model.vo.backContentVo;
import cn.imakerlab.bbs.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.kerberos.KeyTab;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ArticleServiceImp implements ArticleService {
    @Autowired
    ArticleDao articleDao;

    @Autowired
    UserDao userDao;

    @Autowired
    CommentDao commentDao;

    //通过类型查询文章信息
    @Override
    public List<Article> getArticlesMsgByType(String type) {
        ArticleExample articleExample = new ArticleExample();
        ArticleExample.Criteria criteria = articleExample.createCriteria();

        if (type == null) {
            articleExample.setOrderByClause("release_time DESC");
        } else if (type == "") {
            articleExample.setOrderByClause("release_time DESC");
        } else if (type.equals("favor")) {
            articleExample.setOrderByClause("likes DESC");
        } else if (type.equals("time")) {
            articleExample.setOrderByClause("release_time DESC");
        } else if (type.equals("question")) {
            articleExample.setOrderByClause("release_time DESC");
            criteria.andTypeEqualTo("question");
        } else if (type.equals("activity")) {
            articleExample.setOrderByClause("release_time DESC");
            criteria.andTypeEqualTo("activity");
        } else {
            articleExample.setOrderByClause("release_time DESC");
        }

        List<Article> articles = articleDao.selectByExample(articleExample);
//        ArticleVo articleVo = new ArticleVo();
//        articleVo.setAuthorName();
        return articles;
    }


    @Override
    public List<backContentVo> searchMsgByKey(String key) {
        List<backContentVo> list = new ArrayList<>();

        UserExample userExample = new UserExample();
        UserExample.Criteria userCriteria = userExample.createCriteria();

        ArticleExample articleExample = new ArticleExample();
        articleExample.setDistinct(true);
        ArticleExample.Criteria articleCriteria1 = articleExample.createCriteria();
        articleCriteria1.andTitleLike("%" + key + "%");

        ArticleExample.Criteria articleCriteria2 = articleExample.createCriteria();
        articleCriteria2.andSummaryLike("%" + key + "%");
        articleExample.or(articleCriteria2);

        ArticleExample.Criteria articleCriteria3 = articleExample.createCriteria();
        articleCriteria3.andLabelLike("%" + key + "%");
        articleExample.or(articleCriteria3);

        userCriteria.andUsernameLike("%" + key + "%");
        List<User> users = userDao.selectByExample(userExample);
        if (users != null) {
            for (User user : users) {
                list.add(new backContentVo("author_name", user.getUsername()));
            }
        }

        List<Article> articles = articleDao.selectByExample(articleExample);
        if (articles != null) {
            for (Article article : articles) {
                if (article.getTitle().contains(key)) {
                    list.add(new backContentVo("title", article.getTitle()));
                } else if (article.getSummary().contains(key)) {
                    list.add(new backContentVo("summery", article.getSummary()));
                } else if (article.getLabel().contains(key)) {
                    list.add(new backContentVo("label", article.getLabel()));
                }
            }
        }
        return list;
    }

    @Override
    public String getAuthority(String username) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        User user = (User) userDao.selectByExample(userExample);
        return user.getAuthority();
    }

    @Override
    public UserAndArticleAndCommentsVo getDetailMsgOfArticleByArticleId(Integer id) {
        if (id <= 0) {
            throw new MyException("文章id错误");
        }

        Article article = articleDao.selectByPrimaryKey(id);

        if (article == null) {
            throw new MyException("文章id不存在");
        }

        Integer authorId = article.getAuthorId();
        User user = userDao.selectByPrimaryKey(authorId);

        CommentExample commentExample = new CommentExample();
        CommentExample.Criteria commentExampleCriteria = commentExample.createCriteria();
        commentExampleCriteria.andArticleIdEqualTo(id.toString());
        List<Comment> comments = commentDao.selectByExample(commentExample);

        ArrayList<CommentVo> commentVos = new ArrayList<>();

        for (Comment comment : comments) {
            commentVos.add(new CommentVo(comment.getUserUsername(), comment.getContent(),
                    comment.getCommentTime(), userDao.selectByPrimaryKey(comment.getUserId()).getFigureUrl()));
        }

        UserAndArticleAndCommentsVo userAndArticleAndCommentsVo = new UserAndArticleAndCommentsVo();
        userAndArticleAndCommentsVo.setArticleId(id);
        userAndArticleAndCommentsVo.setAuthorName(user.getUsername());
        userAndArticleAndCommentsVo.setFigureUrl(user.getFigureUrl());
        userAndArticleAndCommentsVo.setLabel(article.getLabel());
        userAndArticleAndCommentsVo.setLikes(article.getLikes());
        userAndArticleAndCommentsVo.setText(article.getText());
        userAndArticleAndCommentsVo.setViews(article.getViews().toString());
        userAndArticleAndCommentsVo.setTalkList(commentVos);


        System.out.println(userAndArticleAndCommentsVo);
        return userAndArticleAndCommentsVo;

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteArticlesByUser(List<Integer> delArticleIdList, String username) {
        ArticleExample articleExample = new ArticleExample();
        ArticleExample.Criteria articleExampleCriteria = articleExample.createCriteria();
        articleExampleCriteria.andIdIn(delArticleIdList);
        articleExampleCriteria.andTypeEqualTo("question");
        articleExampleCriteria.andAuthorNameEqualTo(username);

        Integer count = articleDao.deleteByExample(articleExample);

        if (count != delArticleIdList.size()) {
            throw new MyException("存在文章id不存在，删除失败");
        }
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteNoticesByAdmin(List<Integer> noticeList, String adminName) {
        ArticleExample articleExample = new ArticleExample();
        ArticleExample.Criteria articleExampleCriteria = articleExample.createCriteria();
        articleExampleCriteria.andAuthorNameEqualTo(adminName);
        articleExampleCriteria.andIdIn(noticeList);

        ArticleExample articleExample1 = new ArticleExample();
        ArticleExample.Criteria criteria1 = articleExample1.createCriteria();
        criteria1.andTypeEqualTo("activity");

        ArticleExample articleExample2 = new ArticleExample();
        ArticleExample.Criteria criteria2 = articleExample2.createCriteria();
        criteria2.andTypeEqualTo("notice");

        articleExample1.or(criteria2);
        articleExample.or(criteria1);

        Integer count = articleDao.deleteByExample(articleExample);
        if (count != noticeList.size()) {
            throw new MyException("存在公告id不存在，删除失败");
        }
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer putArticlesByUser(String authorId, String articleId, String text, String title, String label) {
        ArticleExample articleExample = new ArticleExample();
        ArticleExample.Criteria articleExampleCriteria = articleExample.createCriteria();
        articleExampleCriteria.andIdEqualTo(Integer.parseInt(articleId));
        articleExampleCriteria.andAuthorIdEqualTo(Integer.parseInt(authorId));

        Article article = new Article();
        article.setAuthorId(Integer.parseInt(authorId));
        article.setId(Integer.parseInt(articleId));
        article.setText(text);
        article.setTitle(title);
        article.setLabel(label);

        Integer count = articleDao.updateByExampleSelective(article, articleExample);

        if (count!=0&&count!=1){
            throw new MyException("更新出现错误错误，请重试");
        }
        return count;
    }

    @Override
    public Integer putNoticesByAdmin(String authorId, String articleId, String text, String title, String label) {
        ArticleExample articleExample = new ArticleExample();
        ArticleExample.Criteria articleExampleCriteria = articleExample.createCriteria();
        articleExampleCriteria.andIdEqualTo(Integer.parseInt(articleId));
        articleExampleCriteria.andAuthorIdEqualTo(Integer.parseInt(authorId));

        User user = userDao.selectByPrimaryKey(Integer.parseInt(authorId));
        if (!user.getAuthority().equals("ROLE_ADMIN")){
            throw new MyException("您无此权限");
        }

        Article article = new Article();
        article.setAuthorId(Integer.parseInt(authorId));
        article.setId(Integer.parseInt(articleId));
        article.setText(text);
        article.setTitle(title);
        article.setLabel(label);

        Integer count = articleDao.updateByExampleSelective(article, articleExample);

        if (count!=0&&count!=1){
            throw new MyException("更新出现错误错误，请重试");
        }
        return count;
    }
}
