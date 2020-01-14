package cn.imakerlab.bbs.model.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserVo {

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    @NotNull(message = "用户名不能为null")
    @Size(min = 2, max = 15, message ="用户名长度不合理，规定用户名长度为2~15")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @NotNull(message = "密码不能为null")
    @Size(min = 2, max = 15, message ="密码长度不合理，规定密码长度为2~15")
    private String password;

}
