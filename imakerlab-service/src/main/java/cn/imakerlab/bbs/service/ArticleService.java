package cn.imakerlab.bbs.service;

import cn.imakerlab.bbs.model.po.Article;
import cn.imakerlab.bbs.model.po.User;
import cn.imakerlab.bbs.model.po.UserExample;
import cn.imakerlab.bbs.model.vo.UserAndArticleAndCommentsVo;
import cn.imakerlab.bbs.model.vo.backContentVo;

import java.util.List;
import java.util.Map;

public interface ArticleService {
    List<Article> getArticlesMsgByType(String type);
    List<backContentVo> searchMsgByKey(String key);
    String getAuthority(String username);
    UserAndArticleAndCommentsVo getDetailMsgOfArticleByArticleId(Integer id);
    void deleteArticlesByUser(List<Integer> delArticleIdList,String username);
    void deleteNoticesByAdmin(List<Integer> noticeList,String adminName);
    Integer putArticlesByUser(String authorId,String articleId,String text,String title,String label);
    Integer putNoticesByAdmin(String authorId,String articleId,String text,String title,String label);
}
