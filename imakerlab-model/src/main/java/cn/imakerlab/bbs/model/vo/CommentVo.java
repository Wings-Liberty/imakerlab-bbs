package cn.imakerlab.bbs.model.vo;

import cn.imakerlab.bbs.model.po.Comment;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentVo {

    /**
     * 评论的唯一标识符
     */
    private Integer id;

    private String username;

    private String content;

    @JsonFormat(pattern="yyyy/MM/dd")

    private Date commentTime;

    private String figureUrl;

    public CommentVo(Comment comment, String username, String figureUrl){
        this.id = comment.getId();
        this.username = username;
        this.content = comment.getContent();
        this.commentTime = comment.getCommentTime();
        this.figureUrl = figureUrl;

    }

}
