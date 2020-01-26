package cn.imakerlab.bbs.utils;

import cn.imakerlab.bbs.constant.ErrorConstant;
import cn.imakerlab.bbs.constant.FileType;
import cn.imakerlab.bbs.model.exception.MyException;
import cn.imakerlab.bbs.properties.ConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class MyUtils {

    private static ConfigProperties configProperties = new ConfigProperties();
//    @Autowired
//    private static ConfigProperties configProperties;

    public static <T> T ListToOne(List<T> list) {

        //不知道list长度为0是是否返回false
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        if (list.size() == 1) {
            return list.get(0);
        } else {
            throw new MyException("MyUtils的ListToOne方法抛出的异常，因为希望获取到的list不是null但是size也不为1");
        }

    }

    public static String uplode(MultipartFile file, FileType fileType) {

        if (file.isEmpty()) {
            throw new MyException(ErrorConstant.File.FILE_IS_EMPTY);
        }

        if(file.getSize() > configProperties.getFile().getFigureMaxSize()){
            throw new MyException(ErrorConstant.File.FILE_SIZE_EXCEEDS);
        }
        // 获取文件名
        String fileName = UUID.randomUUID().toString() + file.getOriginalFilename();

        String basePath = configProperties.getFile().getFigureFolderUrl();
        System.out.println(basePath);

        //判断路径是否存在，文件夹
        File uploadFile = new File(basePath);
        if (!uploadFile.exists()) {

            uploadFile.mkdirs();
        }
        //使用 MulitpartFile 接口中方法，把上传的文件写到指定位置
        try {
            file.transferTo(new File(uploadFile, fileName));
        } catch (IOException e) {
            throw new MyException(ErrorConstant.File.FILE_UPLOAD_ERROR);
        }
        return basePath + "/" + fileName;
    }

}