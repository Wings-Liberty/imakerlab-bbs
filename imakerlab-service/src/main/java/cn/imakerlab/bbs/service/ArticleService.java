package cn.imakerlab.bbs.service;

import cn.imakerlab.bbs.enums.ArticleTypeEnum;
import cn.imakerlab.bbs.model.po.Article;
import cn.imakerlab.bbs.model.po.User;
import cn.imakerlab.bbs.model.vo.ArticleVo;
import cn.imakerlab.bbs.model.vo.BackContentVo;

import java.util.List;

public interface ArticleService {


    List<ArticleVo> listArticleVosByType(ArticleTypeEnum articleTypeEnum);

    List<BackContentVo> searchMsgByKey(String key);

    void deleteArticlesByUserIdAndDelList(List<Integer> delArticleIdList, Integer userId);

    void putArticlesByUser(int authorId, int articleId, String text, String title, String label);

    User getUserById(Integer userId);

    void postArticleByUser(Integer authorId, List<String> label, String text, String title, String sumamry, String coverUrl);

    void postImage(Integer userId, Integer articleId, String coverUrl);

    List<String> getLabels(Integer userId, boolean isAdmin);

    void updateAtricleLikesByArticleId(Integer articleId, boolean flag);

    /**
     * 获取没有被删除的文章
     * 如果文章的作者用户已被删除，将作者的名字和头像改为被删除用户的名字和头像
     * @param id
     * @return
     */
    Article getNotDeletedArticleByArticleId(int id);

    /**
     * 使用list获取相应的文章
     * @param delArticle
     * @return
     */
    List<Article> listArticleByList(List<Integer> delArticle);

    ArticleVo getNotDeletedArticleVoByArticleId(int articleId);
}
