package cn.imakerlab.bbs.model.po;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * comment
 * @author 
 */
@Data
public class Comment implements Serializable {
    /**
     * 评论的唯一标识符
     */
    private Integer id;

    /**
     * 评论的内容
     */
    private String content;

    /**
     * 评论的时间
     */
    private Date commentTime;

    /**
     * 评论者用户名
     */
    private String userUsername;

    /**
     * 评论者的id
     */
    private Integer userId;

    /**
     * 所评论的文章的id
     */
    private Integer articleId;

    /**
     * 是否被删除（逻辑删除）1——已经被删除，2——没有被删除
     */
    private Byte isDeleted;

    /**
     * 该条评论是否作用于文章。1——作用于文章，2——作用于其他评论
     */
    private Byte isEffectArticle;

    private static final long serialVersionUID = 1L;
}