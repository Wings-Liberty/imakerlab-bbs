package cn.imakerlab.bbs.test;

import cn.imakerlab.bbs.mapper.UserDao;
import cn.imakerlab.bbs.model.po.User;
import cn.imakerlab.bbs.model.po.UserExample;
import cn.imakerlab.bbs.service.Imp.UserServiceImp;
import cn.imakerlab.bbs.service.UserService;
import cn.imakerlab.bbs.web.WebApplication;
import javafx.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WebApplication.class)
public class test {

    @Autowired
    UserDao userDao;

    @Test
    public void testMybatisIn(){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add(i);
        }

        UserExample example =  new UserExample();
        example.createCriteria().andIdIn(list);

        User user = new User();
        user.setSlogan("批量修改的个性签名");

        int count = userDao.updateByExampleSelective(user, example);

        System.out.println("受影响的行数：" + count);

    }

}