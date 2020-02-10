package cn.imakerlab.bbs.test;

import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

public class testJava {

    @Test
    public void test() {

        try {
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            System.out.println(path.getAbsolutePath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

}
