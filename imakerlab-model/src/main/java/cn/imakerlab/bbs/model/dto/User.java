package cn.imakerlab.bbs.model.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * user
 * @author 
 */
@Data
public class User implements Serializable {
    /**
     * 用户id
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像的url，图片存后端文件夹里
     */
    private String figureUrl;

    /**
     * 标语/个性签名
     */
    private String slogan;

    private static final long serialVersionUID = 1L;
}