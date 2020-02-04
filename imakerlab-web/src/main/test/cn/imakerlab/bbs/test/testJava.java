package cn.imakerlab.bbs.test;

import cn.imakerlab.bbs.enums.ArticleTypeEnum;
import org.junit.Test;

public class testJava {

    @Test
    public void test() {
        for (ArticleTypeEnum value : ArticleTypeEnum.values()) {
            System.out.println(value);
        }
    }

}
