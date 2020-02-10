package cn.imakerlab.bbs.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class testJava {

    @Test
    public void test() {

        List<Integer> list1 = new ArrayList<>();
        list1.add(1);

        List<Integer> list2 = new ArrayList<>();
        list1.add(2);

        List<Integer> list = new ArrayList<>();
        list1.addAll(list2);

        System.out.println(list1);

    }

}
