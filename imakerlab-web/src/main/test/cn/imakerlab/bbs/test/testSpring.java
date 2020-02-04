package cn.imakerlab.bbs.test;

import cn.imakerlab.bbs.enums.ArticleTypeEnum;
import cn.imakerlab.bbs.web.WebApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WebApplication.class)
public class testSpring {

    @Test
    public void test(){
        for (ArticleTypeEnum value : ArticleTypeEnum.values()) {
            System.out.println(value);
        }
    }

}