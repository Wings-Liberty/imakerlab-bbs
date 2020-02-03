package cn.imakerlab.bbs.service.Imp;

import cn.imakerlab.bbs.mapper.ArticleDao;
import cn.imakerlab.bbs.mapper.CommentDao;
import cn.imakerlab.bbs.mapper.LikeDao;
import cn.imakerlab.bbs.mapper.UserDao;
import cn.imakerlab.bbs.model.exception.MyException;
import cn.imakerlab.bbs.model.po.*;

import cn.imakerlab.bbs.model.vo.BackContentVo;
import cn.imakerlab.bbs.model.vo.CommentVo;
import cn.imakerlab.bbs.model.vo.GetArticlesMsgByTypeVo;
import cn.imakerlab.bbs.model.vo.UserAndArticleAndCommentsVo;

import cn.imakerlab.bbs.service.ArticleService;
import cn.imakerlab.bbs.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
@Slf4j
@Service
public class ArticleServiceImp implements ArticleService {
    @Autowired
    ArticleDao articleDao;

    @Autowired
    UserDao userDao;

    @Autowired
    CommentDao commentDao;

    @Autowired
    LikeDao likeDao;

    //通过类型查询文章信息
    @Override
    public List<GetArticlesMsgByTypeVo> getArticlesMsgByType(String type) {

        ArticleExample articleExample = new ArticleExample();
        ArticleExample.Criteria criteria = articleExample.createCriteria();

        if (type == null||type == "") {

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

        List<GetArticlesMsgByTypeVo> getArticlesMsgByTypeVos = new ArrayList<>();

        for (Article article : articles) {
            GetArticlesMsgByTypeVo getArticlesMsgByTypeVo = new GetArticlesMsgByTypeVo();

            getArticlesMsgByTypeVo.setAuthorName(article.getAuthorName());
            getArticlesMsgByTypeVo.setCoverUrl(article.getCoverUrl());
            getArticlesMsgByTypeVo.setLabel(article.getLabel());
            getArticlesMsgByTypeVo.setLikes(article.getLikes());
            getArticlesMsgByTypeVo.setReleaseTime(article.getReleaseTime());
            getArticlesMsgByTypeVo.setTitle(article.getTitle());
            getArticlesMsgByTypeVo.setViews(article.getViews());
            getArticlesMsgByTypeVo.setSummary(article.getSummary());

            getArticlesMsgByTypeVos.add(getArticlesMsgByTypeVo);
        }

        log.info("下拉刷新查找文章信息成功");
        return getArticlesMsgByTypeVos;
    }


    @Override
    public List<BackContentVo> searchMsgByKey(String key) {

        ArticleExample articleExample1 = new ArticleExample();
        ArticleExample.Criteria articleExampleCriteria1 = articleExample1.createCriteria();
        articleExampleCriteria1.andTitleLike("%"+key+"%");

        ArticleExample articleExample2 = new ArticleExample();
        ArticleExample.Criteria articleExampleCriteria2 = articleExample2.createCriteria();
        articleExampleCriteria2.andAuthorNameLike("%"+key+"%");

        ArticleExample articleExample3 = new ArticleExample();
        ArticleExample.Criteria articleExampleCriteria3 = articleExample3.createCriteria();
        articleExampleCriteria3.andSummaryLike("%"+key+"%");

        articleExample1.setDistinct(true);
        articleExample1.or(articleExampleCriteria2);
        articleExample1.or(articleExampleCriteria3);

        List<Article> articles = articleDao.selectByExample(articleExample1);

        List<BackContentVo> backContentVos = new ArrayList<>();

        List<Article> articles1=null;

        if (articles.size() >= 10) {

            articles1 = articles.subList(0, 9);
        }else {

            articles1=articles;
        }

        if (articles1 != null) {
            for (Article article : articles1) {
                if (article.getAuthorName().contains(key)){
                    BackContentVo backContentVo = new BackContentVo();
                    backContentVo.setType("author_name");
                    backContentVo.setContent(article.getAuthorName());
                    backContentVos.add(backContentVo);
                }else if (article.getTitle().contains(key)){
                    BackContentVo backContentVo = new BackContentVo();
                    backContentVo.setType("title");
                    backContentVo.setContent(article.getTitle());
                    backContentVos.add(backContentVo);
                }else if (article.getSummary().contains(key)){
                    BackContentVo backContentVo = new BackContentVo();
                    backContentVo.setType("summary");
                    backContentVo.setContent(article.getSummary());
                    backContentVos.add(backContentVo);
                }
            }
        }

        return backContentVos;

    }

    @Override
    public Map<String,List<String>>  getLabel(Integer userId) {

        User user = getUserById(userId);

        ArrayList<String> sorts = new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();

        if (user.getAuthority().equals("ROLE_ADMIN")||user.getAuthority().contains("ROLE_ADMIN")){

            sorts.add("Notices");
            sorts.add("Activities");

            map.put("label",sorts);
            return map;
        }else {

            ArticleExample articleExample = new ArticleExample();
            ArticleExample.Criteria articleExampleCriteria = articleExample.createCriteria();
            articleExampleCriteria.andIdGreaterThanOrEqualTo(1);

            List<Article> articles = articleDao.selectByExample(articleExample);

            for (Article article : articles) {

                String label1 = article.getLabel().replace(" ","");
                String label2 = label1.substring(1, label1.length() - 1);
                String[] split = label2.split(",");

                for (String s : split) {
                    if (!sorts.contains(s)) {
                        sorts.add(s);
                    }
                }
            }
            map.put("label",sorts);
            return map;
        }
    }

    @Override
    public UserAndArticleAndCommentsVo getDetailMsgOfArticleByArticleId(Integer id) {

        Article article = articleDao.selectByPrimaryKey(id);

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

        return userAndArticleAndCommentsVo;

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteArticlesByUser(List<Integer> delArticleIdList,Integer userId) {

        User user = getUserById(userId);
        String username = user.getUsername();

        if (user.getAuthority().equals("ROLE_ADMIN")){

            if (delArticleIdList.size()==0||delArticleIdList==null){
                throw new MyException("没有选择公告Id");
            }

            ArticleExample articleExample = new ArticleExample();
            ArticleExample.Criteria articleExampleCriteria = articleExample.createCriteria();
            articleExampleCriteria.andAuthorNameEqualTo(username);
            articleExampleCriteria.andAuthorIdEqualTo(userId);
            articleExampleCriteria.andIdIn(delArticleIdList);

            ArticleExample articleExample1 = new ArticleExample();
            ArticleExample.Criteria criteria1 = articleExample1.createCriteria();
            criteria1.andTypeEqualTo("activity");

            ArticleExample articleExample2 = new ArticleExample();
            ArticleExample.Criteria criteria2 = articleExample2.createCriteria();
            criteria2.andTypeEqualTo("notice");

            articleExample1.or(criteria2);
            articleExample.or(criteria1);

            Article article = new Article();

            article.setIsDeleted(Byte.parseByte("0"));

            Integer count = articleDao.updateByExampleSelective(article,articleExample);

            if (count != delArticleIdList.size()) {
                throw new MyException("删除公告失败");
            }

            log.info("公告删除成功");

        }else {

            if (delArticleIdList.size()==0||delArticleIdList==null){
                throw new MyException("没有选择文章Id");
            }

                ArticleExample articleExample = new ArticleExample();
                ArticleExample.Criteria articleExampleCriteria = articleExample.createCriteria();
                articleExampleCriteria.andIdIn(delArticleIdList);
                articleExampleCriteria.andAuthorIdEqualTo(userId);
                articleExampleCriteria.andTypeEqualTo("question");
                articleExampleCriteria.andAuthorNameEqualTo(username);

                Article article = new Article();
                article.setIsDeleted(Byte.parseByte("0"));

                Integer count = articleDao.updateByExampleSelective(article,articleExample);

            if (count != delArticleIdList.size()) {
                throw new MyException("删除文章失败");
            }

            log.info("文章删除成功");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void putArticlesByUser(String authorId, String articleId, String text, String title, String label) {

        User user = getUserById(Integer.parseInt(authorId));

        if (user.getAuthority().equals("ROLE_ADMIN")||user.getAuthority().contains("ROLE_ADMIN")){

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

            log.info("更新公告成功");

        }else {

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

            log.info("更新文章成功");
        }

    }

    @Override
    public User getUserById(Integer userId) {
        User user = userDao.selectByPrimaryKey(userId);
        if (user == null) {
            throw  new MyException("此用户id不存在");
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
        article.setLabel(label.toString());
        article.setAuthorId(authorId);
        article.setTitle(title);
        article.setText(text);
        article.setSummary(sumamry);
        article.setCoverUrl(coverUrl);
        article.setLastModified(date);
        article.setReleaseTime(date);
        article.setIsDeleted(Byte.parseByte("0"));
        article.setType("question");
        Integer count = articleDao.insertSelective(article);

        if (count==0){
            throw new MyException("添加文章失败");
        }

        log.info("增加文章信息成功");

    }

    @Override
    public void postImage(Integer userId,Integer articleId,String coverUrl) {

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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void postLikesByUser(Integer userId, Integer articleId) {

        LikeExample likeExample = new LikeExample();
        LikeExample.Criteria criteria = likeExample.createCriteria();
        criteria.andArticleIdEqualTo(articleId);
        criteria.andUserIdEqualTo(userId);

        Like like = (Like) likeDao.selectByExample(likeExample);

        Article article = articleDao.selectByPrimaryKey(articleId);

        Article articleTo = new Article();
        articleTo.setId(articleId);

        Like like1 = new Like();
        if (like==null){
            like1.setArticleId(articleId);
            like1.setUserId(userId);
            like1.setIsLike(Byte.parseByte("1"));

            Integer count = likeDao.insertSelective(like1);

            if (count != 1) {
                throw new MyException("点赞失败");
            }
            articleTo.setLikes(article.getLikes()+1);

            int i = articleDao.updateByPrimaryKeySelective(articleTo);

            if (i!=1){
                throw new MyException("点赞失败");
            }

            log.info("点赞成功");

        }else {
            if (like.getIsLike()==1){
                like1.setIsLike(Byte.parseByte("0"));
                Integer count = likeDao.updateByPrimaryKeySelective(like1);

                if (count != 1) {
                    throw new MyException("取消点赞失败");
                }

                articleTo.setLikes(article.getLikes()-1);
                int i = articleDao.updateByPrimaryKeySelective(articleTo);

                if (i != 1) {
                    throw new MyException("取消点赞失败");
                }
                log.info("取消点赞成功");
            }else {
                like1.setIsLike(Byte.parseByte("1"));
                int count = likeDao.updateByPrimaryKeySelective(like1);
                if (count != 1) {
                    throw new MyException("点赞失败");
                }

                articleTo.setLikes(article.getLikes());
                int i = articleDao.updateByPrimaryKeySelective(articleTo);

                if (i != 1) {
                    throw new MyException("点赞失败");
                }

                log.info("点赞成功");
            }
        }
    }
}
