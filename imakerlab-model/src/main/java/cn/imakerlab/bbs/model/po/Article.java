package cn.imakerlab.bbs.model.po;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * article
 * @author 
 */
@Data
public class Article implements Serializable {
    /**
     * 文章id
     */
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 摘要
     */
    private String summary;

    /**
     * 正文
     */
    private String text;

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
    private Date releaseTime;

    /**
     * 最近的修改时间
     */
    private Date lastModified;

    /**
     * 删除时间
     */
    private Date deleteTime;

    /**
     * 标签
     */
    private String label;

    /**
     * 作者名
     */
    private String authorName;

    /**
     * 作者id
     */
    private Integer authorId;

    /**
     * 是否被删除（逻辑删除）1——已经被删除，2——没有被删除
     */
    private Byte isDeleted;

    private String type;

    private static final long serialVersionUID = 1L;
}