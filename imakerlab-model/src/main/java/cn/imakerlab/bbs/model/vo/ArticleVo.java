package cn.imakerlab.bbs.model.vo;

import cn.imakerlab.bbs.model.po.Article;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleVo {
    public ArticleVo(Article article) {
        id=article.getId();
        coverUrl=article.getCoverUrl();
        authorName=article.getAuthorName();
        summary=article.getSummary();
        releaseTime=article.getReleaseTime();
        likes=article.getLikes();
        views=article.getViews();
        title=article.getTitle();
        label=article.getLabel();

    }
    private Integer id;
    private String coverUrl;
    private String authorName;
    private String summary;
    @JsonFormat(pattern="yyyy/MM/dd")
    private Date releaseTime;
    private Integer likes;
    private Integer views;
    private String title;
    private String label;
}
