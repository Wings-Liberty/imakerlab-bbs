package cn.imakerlab.bbs.test;

public class test {

    public static void main(String[] args) throws Exception {

        System.out.println("抛异常前");

        try {
            throw new Exception("异常");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}