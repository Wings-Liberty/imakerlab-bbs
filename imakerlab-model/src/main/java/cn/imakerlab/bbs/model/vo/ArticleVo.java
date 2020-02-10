package cn.imakerlab.bbs.model.vo;

import cn.imakerlab.bbs.model.po.Article;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleVo {

    private Integer id;

    private String coverUrl;

    private String authorName;

    private String summary;

    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date releaseTime;

    private Integer likes;

    private Integer views;

    private String title;

    private List label;

    /**
     * 作者头像的url
     */
    private String authorFigureUrl;

    public ArticleVo(Article article) {
        this.id = article.getId();
        this.coverUrl = article.getCoverUrl();
        this.authorName = article.getAuthorName();
        this.summary = article.getSummary();
        this.releaseTime = article.getReleaseTime();
        this.likes = article.getLikes();
        this.views = article.getViews();
        this.title = article.getTitle();
        this.label = Arrays.asList(article.getLabel().split(","));
    }

}