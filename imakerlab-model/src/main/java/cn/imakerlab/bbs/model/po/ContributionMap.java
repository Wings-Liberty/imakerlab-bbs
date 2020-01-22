package cn.imakerlab.bbs.model.po;

import java.io.Serializable;
import lombok.Data;

/**
 * contribution_map
 * @author 
 */
@Data
public class ContributionMap implements Serializable {
    /**
     * 唯一标识符
     */
    private Integer id;

    /**
     * 记录该条数据的年份
     */
    private Integer year;

    /**
     * 记录该条数据时所在年份的周序数
     */
    private Integer week;

    /**
     * 用户的id
     */
    private Integer userId;

    /**
     * 本周新文章数，特指新文章。修改旧文章，发布新公告等均不算此范围中
     */
    private Integer num;

    private static final long serialVersionUID = 1L;
}