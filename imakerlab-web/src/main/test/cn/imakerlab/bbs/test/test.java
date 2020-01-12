package cn.imakerlab.bbs.test;

import cn.imakerlab.bbs.model.dto.User;
import org.springframework.util.ObjectUtils;

public class test {

    public static void main(String[] args) {
        User user = new User();
        System.out.println(ObjectUtils.isEmpty(user));
    }

}