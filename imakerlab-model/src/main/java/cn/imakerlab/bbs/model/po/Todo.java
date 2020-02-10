package cn.imakerlab.bbs.model.po;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * todo
 * @author 
 */
@Data
public class Todo implements Serializable {
    /**
     * 唯一标识符
     */
    private Integer id;

    /**
     * 目标内容
     */
    @NotBlank
    private String plan;

    /**
     * 计划是否完成
     */
    private Byte status;

    /**
     * 计划完成时间
     */
    private Date completeTime;

    /**
     * 是否被删除（逻辑删除）1——已经被删除，2——没有被删除
     */
    private Byte isDeleted;

    /**
     * 用户的id
     */
    private Integer userId;

    private static final long serialVersionUID = 1L;
}