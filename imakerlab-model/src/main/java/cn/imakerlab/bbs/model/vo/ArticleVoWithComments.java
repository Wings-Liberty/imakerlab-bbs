package cn.imakerlab.bbs.model.vo;

import cn.imakerlab.bbs.model.po.Article;
import cn.imakerlab.bbs.model.po.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleVoWithComments {

//    private String authorName;
//
//    private String figureUrl;
//
//    private Integer articleId;
//
//    private Integer likes;
//
//    private String views;
//
//    private String text;
//
//    private String label;

    private ArticleVo article;

    private List<CommentVo> talkList;

//    public ArticleVoWithComments(ArticleVo articleVo, List<CommentVo> talkList) {
//        this.article = articleVo;
//        this.talkList = talkList;
//    }
}
