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

        //用户能拥有的todo的最大数量
        int USER_TODO_MAX_COUNT = 5;

        //被删除的用户的用户名
        String DELETED_USER_USERNAME = "该用户已被删除";

        //被删除的用户的默认头像的url
        String DELETED_USER_FIGURE_URL = "/default/deleted/user/url";

        //被删除的用户的默认头像的url
        String USER_FIGURE_URL = "/default/user/url";
    }

    interface Page {
        //每页的数据量
        int PAGE_SIZE = 10;
        //导航页显示的页数
        int NAVAGATE_PAGES = 5;
    }

    interface Comment{
        //评论的字数上限
        int COMMENT_MAX_LENGTH = 200;
    }

}