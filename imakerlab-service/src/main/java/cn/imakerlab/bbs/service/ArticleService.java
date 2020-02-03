package cn.imakerlab.bbs.service;

import cn.imakerlab.bbs.model.po.User;
import cn.imakerlab.bbs.model.vo.BackContentVo;
import cn.imakerlab.bbs.model.vo.ArticleVo;
import cn.imakerlab.bbs.model.vo.ArticleWithComments;

import java.util.List;
import java.util.Map;

public interface ArticleService {
    List<ArticleVo> getArticlesMsgByType(String type);
    List<BackContentVo> searchMsgByKey(String key);

    ArticleWithComments getDetailMsgOfArticleByArticleId(Integer id);
    void deleteArticlesByUser(List<Integer> delArticleIdList,Integer userId);
//    void deleteNoticesByAdmin(List<Intvodieger> noticeList,String adminName);
    void putArticlesByUser(String authorId,String articleId,String text,String title,String label);
//    Integer putNoticesByAdmin(String authorId,String articleId,String text,String title,String label);
    User getUserById(Integer userId);
    void postArticleByUser(Integer authorId,List<String> label,String text,String title,String sumamry,String coverUrl);
    void postImage(Integer userId,Integer articleId,String coverUrl);
    void postLikesByUser(Integer userId,Integer articleId);
    Map<String,List<String>> getLabel(Integer userId);
}
