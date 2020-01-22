package cn.imakerlab.bbs.service.Imp;

import cn.imakerlab.bbs.constant.ErrorConstant;
import cn.imakerlab.bbs.mapper.UserDao;
import cn.imakerlab.bbs.model.po.User;
import cn.imakerlab.bbs.model.po.UserExample;
import cn.imakerlab.bbs.model.exception.MyException;
import cn.imakerlab.bbs.model.vo.UserVo;
import cn.imakerlab.bbs.service.UserService;
import cn.imakerlab.bbs.utils.MyUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserDao userDao;

    @Override
    public void register(UserVo userVo) {

        if (isExistUsername(userVo.getUsername())) {
            logger.info("用户名：'" + userVo.getUsername() + "'已存在");
            throw new MyException(ErrorConstant.User.USER_NAME_EXIT);
        }

        logger.info("没有和'" + userVo.getUsername() + "'重复的名字，这个名字可以注册账号");

        User user = new User();

        user.setUsername(userVo.getUsername());
        user.setPassword(passwordEncoder.encode(userVo.getPassword()));
        user.setFigureUrl("");
        user.setSlogan("");
        user.setStars(0);
        user.setAuthority("");
        user.setIsDeleted((byte)0);
        user.setArticleNum(0);

        userDao.insertSelective(user);

    }

    @Override
    public boolean isExistUsername(String username) {

        boolean result = true;

        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username);

        User user = MyUtils.ListToOne(userDao.selectByExample(example));

        if (user == null) {
            result = false;
        } else if (StringUtils.isEmpty(user.getUsername())) {
            result = false;
        }

        return result;
    }

    @Override
    public User getUserByAuthentication(Authentication authentication) {

        String username = authentication.getName();

        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username);

        User user = MyUtils.ListToOne(userDao.selectByExample(example));

        user.setPassword("");

        return user;

    }

    @Override
    public void setFigureUrl(String figureUrl, String username) {

        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username);

        User user = new User();
        user.setFigureUrl(figureUrl);

        userDao.updateByExampleSelective(user, example);
    }

    @Override
    public void setSlogan(String slogan, String username, String newUsername) {

        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username);

        User user = new User();
        user.setSlogan(slogan);
        user.setUsername(newUsername);

        userDao.updateByExampleSelective(user, example);

    }

    @Override
    public UserVo getUserVoById(int id) {

        logger.info("getUserVoById可以使用");

        User user = userDao.selectByPrimaryKey(id);

        if(user == null){
            throw new MyException("找不到该用户的信息");
        }

        UserVo userVo = new UserVo(user);

        return userVo;

    }

    @Override
    public List<User> getAllNotDeletedUser() {

        System.out.println("进入userservice");

        UserExample example = new UserExample();
        example.createCriteria().andIsDeletedEqualTo((byte) 0);

        List<User> userList = userDao.selectByExample(example);

        return userList;

    }

    @Override
    public User getUseraaa() {
        User user = userDao.selectByPrimaryKey(19);

        return user;
    }

}