package cn.imakerlab.bbs.service;

import cn.imakerlab.bbs.model.dto.User;
import cn.imakerlab.bbs.model.vo.UserVo;
import org.springframework.security.core.Authentication;

public interface UserService {

    void register(UserVo userVo);

    boolean isExistUsername(String username);

    User getUserByAuthentication(Authentication authentication);

    void setFigureUrl(String figureUrl, String username);

    void setSlogan(String slogan, String username);
}