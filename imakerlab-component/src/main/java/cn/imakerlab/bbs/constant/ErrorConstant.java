package cn.imakerlab.bbs.constant;

public interface ErrorConstant {

    interface User{
        String USER_NAME_NOTFOUND = "用户名找不到";
        String USER_NAME_EXIT = "用户名已存在";
        String USER_SLOGAN_SIZE_EXECEEDS = "用户的个性签名长度超出限制";
    }

    interface Article{
        String SEARCH_CONTENT_NULL="搜索内容不能为空";
    }
    interface File {
        String FILE_IS_EMPTY = "文件是空的";
        String FILE_UPLOAD_ERROR = "文件上传失败";
        String FILE_SIZE_EXCEEDS = "文件大小超出限制";
    }

    interface Token{
        String TOKEN_IS_EXPIRED = "令牌过期";
    }

    interface Field{
        String ARRAY_IS_EMPTYORNULL = "数组要再是空的，我三天之内杀了你";
    }
}
