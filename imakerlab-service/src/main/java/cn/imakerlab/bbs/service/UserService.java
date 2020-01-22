package cn.imakerlab.bbs.service;

import cn.imakerlab.bbs.model.po.User;
import cn.imakerlab.bbs.model.vo.UserVo;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface UserService {

    void register(UserVo userVo);

    boolean isExistUsername(String username);

    User getUserByAuthentication(Authentication authentication);

    void setFigureUrl(String figureUrl, String username);

    void setSlogan(String username, String newSlogan, String newUsername);

    UserVo getUserVoById(int id);

    List<User> getAllNotDeletedUser();

    User getUseraaa();
}