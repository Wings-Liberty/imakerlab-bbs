package cn.imakerlab.bbs.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetArticlesMsgByTypeVo {
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
