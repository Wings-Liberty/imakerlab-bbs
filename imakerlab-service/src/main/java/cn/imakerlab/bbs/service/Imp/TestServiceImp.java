package cn.imakerlab.bbs.service.Imp;

import cn.imakerlab.bbs.mapper.UserDao;
import cn.imakerlab.bbs.model.po.User;
import cn.imakerlab.bbs.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImp implements TestService {

    @Autowired
    UserDao userDao;

    @Override
    public User getUser() {
        User user = userDao.selectByPrimaryKey(1);
        System.out.println(user);
        return user;
    }
}
