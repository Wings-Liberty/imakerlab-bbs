package cn.imakerlab.bbs.model.vo;

import cn.imakerlab.bbs.model.po.User;
import lombok.Data;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserVo {

    /**
     * 用户id
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户权限。具体有哪些权限，代码的权限枚举类里自己找
     */
    private String authority;

    /**
     * 该用户发布的文章总数。修改文章和发布公告均不计算在其中
     */
    private Integer articleNum;

    /**
     * 头像的url，图片存后端文件夹里
     */
    private String figureUrl;

    /**
     * 用户个人获赞数，获赞数为该用户所有文章获赞数的总和
     */
    private Integer stars;

    /**
     * 标语/个性签名
     */
    private String slogan;

    public UserVo() {
    }

    public UserVo(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.authority = user.getAuthority();
        this.articleNum = user.getArticleNum();
        this.figureUrl = user.getFigureUrl();
        this.stars = user.getStars();
        this.slogan = user.getSlogan();
    }

}