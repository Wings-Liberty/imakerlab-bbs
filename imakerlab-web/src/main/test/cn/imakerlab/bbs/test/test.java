package cn.imakerlab.bbs.test;

import cn.imakerlab.bbs.model.dto.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;

import java.util.Scanner;

public class test {

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
            String str = null;
            if(StringUtils.isEmpty(str)){
                System.out.println("str是空的或者是null");
            }else {
                System.out.println(str);
            }
    }

}