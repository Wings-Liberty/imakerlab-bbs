package cn.imakerlab.bbs.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentVo {
    private String userUsername;
    private String content;
    private Date commentTime;
    private String figureUrl;
}
