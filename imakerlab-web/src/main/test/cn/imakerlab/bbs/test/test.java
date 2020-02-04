package cn.imakerlab.bbs.test;

import cn.imakerlab.bbs.constant.FileUploadEnum;
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
public class test {

    @Autowired
    UserDao userDao;

    @Test
    public void testAuto(){
    }

    @Test
    public void test(){

    }

}