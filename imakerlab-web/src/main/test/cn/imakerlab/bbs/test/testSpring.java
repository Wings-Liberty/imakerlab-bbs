package cn.imakerlab.bbs.test;

import cn.imakerlab.bbs.mapper.ArticleDao;
import cn.imakerlab.bbs.mapper.LikesDao;
import cn.imakerlab.bbs.mapper.UserDao;
import cn.imakerlab.bbs.model.po.Likes;
import cn.imakerlab.bbs.model.po.LikesExample;
import cn.imakerlab.bbs.model.po.User;
import cn.imakerlab.bbs.model.po.UserExample;
import cn.imakerlab.bbs.web.WebApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WebApplication.class)
public class testSpring {

    @Autowired
    UserDao userDao;
    @Autowired
    ArticleDao articleDao;
    @Autowired
    LikesDao likesDao;

    @Test
    public void test(){

        User user = userDao.selectByPrimaryKey(22);
        System.out.println(user);

        User user1 = userDao.selectByPrimaryKey(1111111);
        System.out.println(user1);

        UserExample example = new UserExample();
        example.createCriteria().andIsDeletedEqualTo((byte) 0);

        List<User> userList = userDao.selectByExample(example);
        System.out.println(userList);

        UserExample example1 = new UserExample();
        example1.createCriteria().andUsernameEqualTo("asfsdafsagsagsdgs");

        List<User> userList1 = userDao.selectByExample(example1);
        System.out.println(userList1);



    }

}