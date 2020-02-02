package cn.imakerlab.bbs.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleVo {

    /**
     * 标题
     */
    private String title;

    /**
     * 摘要
     */
    private String summary;


    /**
     * 封面图的url
     */
    private String coverUrl;

    /**
     * 点赞数
     */
    private Integer likes;

    /**
     * 评论数
     */
    private Integer views;

    /**
     * 发布时间
     */
    @JsonFormat(pattern="yyyy年MM月dd日 HH时mm分ss秒")
    private Date releaseTime;


    /**
     * 标签
     */
    private String label;

    /**
     * 作者名
     */
    private String authorName;



}
