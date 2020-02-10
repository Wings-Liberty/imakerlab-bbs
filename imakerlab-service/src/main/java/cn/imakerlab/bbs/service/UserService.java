package cn.imakerlab.bbs.service;

import cn.imakerlab.bbs.model.po.User;
import cn.imakerlab.bbs.model.vo.UserVo;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UserService {

    /**
     * 封装一个user对象存入数据库
     * 这个方法会对密码加密
     * @param username
     * @param password
     */
    void register(String username, String password);

    /**
     * 检查数据库中是否有相同的用户名
     * @param username
     * @return
     */
    boolean isExistUsername(String username);

    /**
     * 查找指定id的用户，找不到就抛异常
     * @param userId
     * @return
     */
    User getUserByUserId(int userId);

    void setFigureUrl(String figureUrl, int id);

    void setSloganAndUsernameByUserId(int userId, String newSlogan, String newUsername);

    UserVo getUserVoById(int userId);

    List<User> listAllNotDeletedUsers(String authority);

    void deleteUserByList(List<Integer> delList, String authority);

    void updatePasswordByUserId(int userId, String oldPassword, String newPassword);

    /**
     * 修改指定用户的获赞总数
     * @param userId
     * @param flag 如果该值为true，用户获赞总数加一；如果为false，获赞总数减一
     */
    void updateStarts(int userId, boolean flag);

    /**
     * 查找指定id的用户，如果找不到或找到用户后该用户已被删除，就抛异常
     * @param userId
     * @return
     */
    UserVo getNotDeleteedUserVoById(int userId);

    /**
     * 用传来的user对象修改数据库user表的数据
     * @param user
     */
    void updateUserByUserPo(User user);

    /**
     * 指定id的用户的文章数量加一
     * @param userId
     */
    void updateUserArticleNumAddOneByUserId(Integer userId);

    /**
     * 获取所有没有被删除的user
     * 参数authority，暂时没有用到。这是为了以后方便修改留的参数，不要删除
     * @param authority
     * @return
     */
    List<UserVo> listAllNotDeletedUserVos(String authority);

    /**
     * 使用用户id获取用户名
     * 如果该用户已被删除，那么用户名使用默认值
     * 如果用户没被删除，那么用户名使用当前user表里的值
     * @param userId
     * @return
     */
    String getUsernameByUserId(Integer userId);
}