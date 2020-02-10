package cn.imakerlab.bbs.constant;

public interface ErrorConstant {

    interface User{
        String USER_NAME_NOTFOUND = "用户名找不到";
        String USER_NAME_EXIT = "用户名已存在";
        String USER_SLOGAN_SIZE_EXECEEDS = "用户的个性签名长度超出限制";
        String USER_NAME_NOT_FORMAT = "用户名格式违法，正确格式为字母，汉字，数字开头，且长度为1~8";
        String USER_PASSWORD_NOT_FORMAT = "密码长度为8~16";
        String USER_NOT_FOUND = "找不到用户";
        String USER_IS_DELETED = "用户已被删除";
        String PASSWORD_NOT_MATCH = "密码错误，请重新输入";
    }

    interface Article{
        String SEARCH_CONTENT_NULL="搜索内容不能为空";
        String ARTICLE_IS_NOT_FOUND = "找不到该文章";
        String ARTICLE_IS_DELETED = "该文章已经被删了";
    }
    interface File {
        String FILE_IS_EMPTY = "文件是空的";
        String FILE_UPLOAD_ERROR = "文件上传失败";
        String FILE_SIZE_EXCEEDS = "文件大小超出限制";
    }

    interface Token{
        String TOKEN_IS_EXPIRED = "令牌过期";
    }

    interface Universal{
        String ARRAY_IS_EMPTYORNULL = "数组是空的";
        String NOT_FOUND = "没有找到到相关内容";
    }

    interface Authority{
        String ACCESS_IS_DENIED = "用户权限不够，一边玩儿去";
    }

    interface Todo{
        String TODO_COUNT_IS_EXECEEDS = "持有todo的数量已达到限制";
    }

    interface Comment{
        String COMMENT_LENGTH_IS_EXECEEDS = "评论字数超出200字的限制了";
    }
}
