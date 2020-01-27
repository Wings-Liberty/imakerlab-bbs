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
    @Size(min = 1, max = 8, message = "用户名长度不合理，规定用户名长度为1~8")
    @Pattern(regexp = "([a-z]|[A-Z]|[0-9]|[\\u4e00-\\u9fa5])+.*",
            message = "用户名格式违法，正确格式为字母，汉字，数字开头")
    private String username;

    /**
     * 密码
     */
    @Size(min = 8, max = 16, message = "密码格式违法")
    private String password;

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

    public UserVo() {
    }

    public UserVo(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = "你在peach";
        this.authority = user.getAuthority();
        this.articleNum = user.getArticleNum();
        this.figureUrl = user.getFigureUrl();
        this.stars = user.getStars();
    }

}