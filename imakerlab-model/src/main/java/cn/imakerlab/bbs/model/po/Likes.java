package cn.imakerlab.bbs.model.po;

import java.io.Serializable;
import lombok.Data;

/**
 * likes
 * @author 
 */
@Data
public class Likes implements Serializable {
    /**
     * 点赞id
     */
    private Integer id;

    private Integer userId;

    private Integer articleId;

    private Byte isLike;

    private Byte isCollected;

    private static final long serialVersionUID = 1L;
}