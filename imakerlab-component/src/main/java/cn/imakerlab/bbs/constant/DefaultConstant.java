package cn.imakerlab.bbs.constant;

public interface DefaultConstant {

    interface Token{
        //获取token执行的方法
        String GET_TOKEN_METHOD = "postAccessToken";
    }

    interface User {
        //用户权限的必要前缀
        String USER_AUTHORITY_PREFIX = "ROLE_";

        //用户名最大长度
        int USER_NAME_MAX_LENGTH = 8;

        //用户的个性签名的最大长度
        int USER_SLOGAN_MAX_LENGTH = 40;
    }

    interface Page {
        //每页的数据量
        int PAGE_SIZE = 10;
        //导航页显示的页数
        int NAVAGATE_PAGES = 5;
    }

}