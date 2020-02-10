package cn.imakerlab.bbs.service.Imp;

import cn.imakerlab.bbs.constant.DefaultConstant;
import cn.imakerlab.bbs.constant.ErrorConstant;
import cn.imakerlab.bbs.enums.ArticleTypeEnum;
import cn.imakerlab.bbs.enums.LabelEnum;
import cn.imakerlab.bbs.enums.RoleEnum;
import cn.imakerlab.bbs.mapper.ArticleDao;
import cn.imakerlab.bbs.mapper.CommentDao;
import cn.imakerlab.bbs.mapper.UserDao;
import cn.imakerlab.bbs.model.exception.MyException;
import cn.imakerlab.bbs.model.po.Article;
import cn.imakerlab.bbs.model.po.ArticleExample;
import cn.imakerlab.bbs.model.po.User;
import cn.imakerlab.bbs.model.vo.ArticleVo;
import cn.imakerlab.bbs.model.vo.BackContentVo;
import cn.imakerlab.bbs.service.ArticleService;
import cn.imakerlab.bbs.utils.MyUtils;
import cn.imakerlab.bbs.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
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
    public List<ArticleVo> listArticleVosByType(ArticleTypeEnum articleTypeEnum) {

        ArticleExample articleExample = new ArticleExample();
        ArticleExample.Criteria criteria = articleExample.createCriteria();

        articleExample.setOrderByClause(articleTypeEnum.getSort());

        if (articleTypeEnum.getArticle()) {
            criteria.andTypeEqualTo(articleTypeEnum.getType());
        }

        List<Article> articleList = articleDao.selectByExample(articleExample);

        List<ArticleVo> articleVoList = new ArrayList<>();

        for (Article article : articleList) {
            articleVoList.add(new ArticleVo(article));
        }

        log.info("下拉刷新查找文章信息成功");

        return articleVoList;
    }


    @Override
    public List<BackContentVo> searchMsgByKey(String key) {

        PageHelper.startPage(1, DefaultConstant.Page.PAGE_SIZE);

        ArticleExample example = new ArticleExample();

        example.or().andAuthorNameLike("%" + key + "%");
        example.or().andSummaryLike("%" + key + "%");
        example.or().andTitleLike("%" + key + "%");

        example.setDistinct(true);

        List<Article> articles = articleDao.selectByExample(example);

        List<BackContentVo> backContentVos = new ArrayList<>();

        if (!CollectionUtils.isEmpty(articles)) {
            for (Article article : articles) {

                BackContentVo backContentVo = new BackContentVo();

                if (article.getAuthorName().contains(key)) {
                    backContentVo.setType("author_name");
                    backContentVo.setContent(article.getAuthorName());
                }
                if (article.getTitle().contains(key)) {
                    backContentVo.setType("title");
                    backContentVo.setContent(article.getTitle());
                }
                if (article.getSummary().contains(key)) {
                    backContentVo.setType("summary");
                    backContentVo.setContent(article.getSummary());
                }
                backContentVos.add(backContentVo);
            }
        }
        return backContentVos;

    }

    @Override
    public List<String> getLabels(Integer userId, boolean isAdmin) {

        List<String> list = new ArrayList<>();

        if (isAdmin) {
            for (LabelEnum label : LabelEnum.values()) {
                list.add(label.getDes());
            }
        } else {
            for (LabelEnum label : LabelEnum.values()) {
                if (!label.isAdminLabel()) {
                    list.add(label.getDes());
                }
            }
        }

        return list;

    }

    @Override
    public void updateAtricleLikesByArticleId(Integer articleId, boolean flag) {

        int updateLikes = flag ? 1 : -1;

        Article article = articleDao.selectByPrimaryKey(articleId);

        article.setLikes(article.getLikes() + updateLikes);

        articleDao.updateByPrimaryKeySelective(article);

    }

    @Override
    public Article getNotDeletedArticleByArticleId(int id) {
        Article article = articleDao.selectByPrimaryKey(id);

        if (article == null) {
            throw new MyException(ErrorConstant.Article.ARTICLE_IS_NOT_FOUND);
        }
        if (article.getIsDeleted() == 1) {
            throw new MyException(ErrorConstant.Article.ARTICLE_IS_DELETED);
        }

        return article;
    }

    @Override
    public List<Article> listArticleByList(List<Integer> delArticle) {

        ArticleExample example = new ArticleExample();
        example.createCriteria().andIdIn(delArticle);

        List<Article> articleList = articleDao.selectByExample(example);

        return articleList;
    }

    @Override
    public ArticleVo getNotDeletedArticleVoByArticleId(int articleId) {

        Article article = getNotDeletedArticleByArticleId(articleId);

        ArticleVo articleVo = new ArticleVo(article);

        User user = userDao.selectByPrimaryKey(article.getAuthorId());
        if(user.getIsDeleted() == 1){
            articleVo.setAuthorName(DefaultConstant.User.DELETED_USER_USERNAME);
            articleVo.setAuthorFigureUrl(DefaultConstant.User.USER_FIGURE_URL);
        }else {
            articleVo.setAuthorName(user.getUsername());
            articleVo.setAuthorFigureUrl(user.getFigureUrl());
        }

        return articleVo;

    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteArticlesByUserIdAndDelList(List<Integer> delArticleIdList, Integer userId) {

        ArticleExample example = new ArticleExample();
        example.createCriteria()
                .andAuthorIdEqualTo(userId)
                .andIdIn(delArticleIdList);

        Article article = new Article();
        article.setIsDeleted((byte) 1);

        articleDao.updateByExampleSelective(article, example);

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void putArticlesByUser(int authorId, int articleId, String text, String title, String label) {

        User user = getUserById(authorId);

        ArticleExample articleExample = new ArticleExample();
        ArticleExample.Criteria articleExampleCriteria = articleExample.createCriteria();
        articleExampleCriteria.andIdEqualTo(articleId);
        articleExampleCriteria.andAuthorIdEqualTo(authorId);

        Article article = new Article();
        article.setAuthorId(authorId);
        article.setId(articleId);
        article.setText(text);
        article.setTitle(title);
        article.setLabel(label);

        if (user.getAuthority().contains(RoleEnum.ROLE_ADMIN.toString())) {

            Integer count = articleDao.updateByExampleSelective(article, articleExample);

            if (count != 0 && count != 1) {
                throw new MyException("更新出现错误错误，请重试");
            }

            log.info("更新公告成功");

        } else {

            articleExampleCriteria.andTypeEqualTo(ArticleTypeEnum.QUESTION.getType());

            Integer count = articleDao.updateByExampleSelective(article, articleExample);

            if (count != 0 && count != 1) {
                throw new MyException("更新出现错误错误，请重试");
            }

            log.info("更新文章成功");
        }

    }

    @Override
    public User getUserById(Integer userId) {
        User user = userDao.selectByPrimaryKey(userId);
        if (user == null) {
            throw new MyException("此用户id不存在");
        }
        return user;
    }

    @Override
    public void postArticleByUser(Integer authorId, List<String> label, String text, String title, String sumamry, String coverUrl) {

        User user = getUserById(authorId);

        if (user == null) {
            throw new MyException("用户id不存在");
        }

        Date date = new Date();
        Article article = new Article();
        article.setAuthorName(user.getUsername());
        article.setLabel(label
                .toString()
                .replace("[", "")
                .replace("]", ""));
        article.setAuthorId(authorId);
        article.setTitle(title);
        article.setText(MyUtils.markdownToHtml(text));
        article.setSummary(sumamry);
        article.setCoverUrl(coverUrl);
        article.setLastModified(date);
        article.setReleaseTime(date);
        article.setIsDeleted(Byte.parseByte("0"));
        article.setType("");
        Integer count = articleDao.insertSelective(article);
        if (count == 0) {
            throw new MyException("添加文章失败");
        }

        log.info("增加文章信息成功");

    }

    @Override
    public void postImage(Integer userId, Integer articleId, String coverUrl) {

        Article article = new Article();
        article.setCoverUrl(coverUrl);
        article.setId(articleId);

        ArticleExample articleExample = new ArticleExample();
        ArticleExample.Criteria articleExampleCriteria = articleExample.createCriteria();
        articleExampleCriteria.andAuthorIdEqualTo(userId);
        Integer count = articleDao.updateByPrimaryKey(article);

        if (count == 0) {
            throw new MyException("发送图片失败");
        }

        log.info("上传图片成功");

    }

}