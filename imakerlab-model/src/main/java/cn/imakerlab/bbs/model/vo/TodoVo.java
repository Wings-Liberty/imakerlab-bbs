package cn.imakerlab.bbs.model.vo;

import java.util.Date;

public class TodoVo {
    /**
     * 唯一标识符
     */
    private Integer id;

    /**
     * 目标内容
     */
    private String plan;

    /**
     * 计划是否完成
     */
    private Byte status;

    /**
     * 计划完成时间
     */
    private Date completeTime;


}
