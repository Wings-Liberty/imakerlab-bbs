package cn.imakerlab.bbs.constant;

public interface ErrorConstant {

    interface User{
        String USER_NAME_NOTFOUND = "用户名找不到";
        String USER_NAME_EXIT = "用户名已存在";
    }

    interface File {
        String FILE_IS_EMPTY = "文件是空的";
        String FILE_UPLOAD_ERROR = "文件上传失败";
        String FILE_SIZE_EXCEEDS = "文件大小超出限制";
    }
}