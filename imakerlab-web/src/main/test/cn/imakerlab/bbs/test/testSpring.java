package cn.imakerlab.bbs.test;

import cn.imakerlab.bbs.enums.ArticleTypeEnum;
import cn.imakerlab.bbs.mapper.UserDao;
import cn.imakerlab.bbs.model.po.User;
import cn.imakerlab.bbs.web.WebApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WebApplication.class)
public class testSpring {

    @Autowired
    UserDao userDao;

    @Test
    public void test(){
        User user = userDao.selectByPrimaryKey(24);
        System.out.println(user);
    }

}