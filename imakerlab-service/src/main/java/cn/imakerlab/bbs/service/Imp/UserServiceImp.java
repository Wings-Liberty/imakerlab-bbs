package cn.imakerlab.bbs.service.Imp;

import cn.imakerlab.bbs.constant.DefaultConsts;
import cn.imakerlab.bbs.constant.ErrorConsts;
import cn.imakerlab.bbs.mapper.UserDao;
import cn.imakerlab.bbs.model.po.User;
import cn.imakerlab.bbs.model.po.UserExample;
import cn.imakerlab.bbs.model.exception.MyException;
import cn.imakerlab.bbs.model.vo.UserVo;
import cn.imakerlab.bbs.service.UserService;
import cn.imakerlab.bbs.utils.MyUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Transactional(rollbackFor = Exception.class)
@Service
public class UserServiceImp implements UserService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserDao userDao;

    @Override
    public void register(String username, String password) {

        if (!MyUtils.matchUsername(username)) {
            throw new MyException(ErrorConsts.User.USER_NAME_NOT_FORMAT);
        }
        if (!MyUtils.matchPassword(password)) {
            throw new MyException(ErrorConsts.User.USER_PASSWORD_NOT_FORMAT);
        }
        if (isExistUsername(username)) {
            logger.info("用户名：'" + username + "'已存在");
            throw new MyException(ErrorConsts.User.USER_NAME_EXIT);
        }

        logger.info("没有和'" + username + "'重复的名字，这个名字可以注册账号");

        User user = new User();

        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setFigureUrl(DefaultConsts.User.USER_FIGURE_URL);
        user.setSlogan("");
        user.setStars(0);
        user.setAuthority("");
        user.setIsDeleted((byte) 0);
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
    public User getUserByUserId(int userId) {

        User user = userDao.selectByPrimaryKey(userId);

        if (user == null) {
            throw new MyException("找不到该用户的信息");
        }

        return user;
    }

    @Override
    public void setFigureUrl(String figureUrl, int id) {

        UserExample example = new UserExample();
        example.createCriteria().andIdEqualTo(id);

        User user = new User();
        user.setFigureUrl(figureUrl);

        userDao.updateByExampleSelective(user, example);
    }

    @Override
    public void setSloganAndUsernameByUserId(int userId, String newSlogan, String newUsername) {

        if (!MyUtils.matchUsername(newUsername)) {
            throw new MyException(ErrorConsts.User.USER_NAME_NOT_FORMAT);
        } else if (isExistUsername(newUsername)) {
            throw new MyException(ErrorConsts.User.USER_NAME_EXIT);
        } else if (newSlogan.length() > DefaultConsts.User.USER_SLOGAN_MAX_LENGTH) {
            throw new MyException(ErrorConsts.User.USER_SLOGAN_SIZE_EXECEEDS);
        }

        User user = new User();
        user.setId(userId);
        user.setSlogan(newSlogan);
        user.setUsername(newUsername);

        userDao.updateByPrimaryKeySelective(user);

    }

    @Override
    public UserVo getUserVoById(int userId) {

        logger.info("getUserVoById可以使用");

        User user = getUserByUserId(userId);

        UserVo userVo = new UserVo(user);

        return userVo;

    }

    @Override
    public List<User> listAllNotDeletedUsers(String authority) {

        UserExample example = new UserExample();

        example.createCriteria().andIsDeletedEqualTo((byte) 0);

        List<User> userList = userDao.selectByExample(example);

        return userList;

    }

    @Override
    public void deleteUserByList(List<Integer> delList, String authority) {

        UserExample example = new UserExample();
        example.createCriteria().andIdIn(delList);

        User user = new User();
        user.setIsDeleted((byte) 1);

        userDao.updateByExampleSelective(user, example);
    }

    @Override
    public void updatePasswordByUserId(int userId, String oldPassword, String newPassword) {

        if (!MyUtils.matchPassword(newPassword)) {
            throw new MyException(ErrorConsts.User.USER_PASSWORD_NOT_FORMAT);
        }

        UserExample example = new UserExample();
        example.createCriteria().andIdEqualTo(userId);

        if (passwordEncoder.matches(oldPassword, userDao.selectByPrimaryKey(userId).getPassword())) {
            logger.info("密码正确，可以修改密码");

            User user = new User();
            user.setPassword(passwordEncoder.encode(newPassword));

            userDao.updateByExampleSelective(user, example);
        } else {
            throw new MyException(ErrorConsts.User.PASSWORD_NOT_MATCH);
        }

    }

    @Override
    public void updateStarts(int userId, boolean flag) {
        int updateStarts = flag ? 1 : -1;

        User user = userDao.selectByPrimaryKey(userId);
        user.setStars(user.getStars() + updateStarts);

        userDao.updateByPrimaryKeySelective(user);
    }

    @Override
    public UserVo getNotDeleteedUserVoById(int userId) {
        User user = getUserByUserId(userId);

        if (user.getIsDeleted() == 1) {
            throw new MyException(ErrorConsts.User.USER_IS_DELETED);
        }

        UserVo userVo = new UserVo(user);

        return userVo;
    }

    @Override
    public void updateUserByUserPo(User user) {

        userDao.updateByPrimaryKeySelective(user);

    }

    @Override
    public void updateUserArticleNumAddOneByUserId(Integer userId) {

        User user = getUserByUserId(userId);

        user.setArticleNum(user.getArticleNum() + 1);

        userDao.updateByPrimaryKeySelective(user);

    }

    @Override
    public List<UserVo> listAllNotDeletedUserVos(String authority) {

        List<User> userList = listAllNotDeletedUsers(authority);

        List<UserVo> userVoList = new ArrayList<>();

        for (User user : userList) {
            userVoList.add(new UserVo(user));
        }

        return userVoList;

    }

    @Override
    public String getUsernameByUserId(Integer userId) {
        User user = getUserByUserId(userId);

        String username = DefaultConsts.User.DELETED_USER_USERNAME;

        if(user.getIsDeleted() == 0){
            username = user.getUsername();
        }

        return username;

    }

}