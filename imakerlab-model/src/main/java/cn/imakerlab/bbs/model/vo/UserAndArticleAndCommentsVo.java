package cn.imakerlab.bbs.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAndArticleAndCommentsVo {
    private String authorName;
    private String figureUrl;
    private Integer articleId;
    private Integer likes;
    private String views;
    private String text;
    private String label;
    private List<CommentVo> talkList;
}
