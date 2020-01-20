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

    /**
     * 用户个人获赞数，获赞数为该用户所有文章获赞数的总和
     */
    private Integer starts;

    /**
     * 用户权限。具体有哪些权限，代码的权限枚举类里自己找
     */
    private String authority;

    /**
     * 是否被删除（逻辑删除）1——已经被删除，2——没有被删除
     */
    private Byte isDeleted;

    /**
     * 该用户发布的文章总数。修改文章和发布公告均不计算在其中
     */
    private Integer articalNum;

    private static final long serialVersionUID = 1L;
}