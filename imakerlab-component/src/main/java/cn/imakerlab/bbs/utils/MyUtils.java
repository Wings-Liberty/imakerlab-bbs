package cn.imakerlab.bbs.utils;

import cn.imakerlab.bbs.constant.DefaultConstant;
import cn.imakerlab.bbs.constant.ErrorConstant;
import cn.imakerlab.bbs.enums.FileUploadEnum;
import cn.imakerlab.bbs.model.exception.MyException;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.options.MutableDataSet;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

@Slf4j
public class MyUtils {

    public static <T> T ListToOne(List<T> list) {

        //不知道list长度为0是是否返回false
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        if (list.size() == 1) {
            return list.get(0);
        } else {
            throw new MyException("MyUtils的ListToOne方法抛出的异常，因为希望获取到的list弟弟size大于1");
        }

    }

    public static String testUpload(MultipartFile file, FileUploadEnum fileUploadEnum){

        File path = null;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(!path.exists()) {
            path = new File("");
        }

        File upload = new File(path.getAbsolutePath(),"static/upload/");
        if(!upload.exists()) {
            upload.mkdirs();
        }

        String fileName = UUID.randomUUID().toString() + file.getOriginalFilename();
        try {
            file.transferTo(new File(path.getAbsoluteFile() + "/static/upload/", fileName));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("错了");
        }


        return path.getAbsoluteFile() + "/static/upload/" + fileName;

    }

    public static String uplode(MultipartFile file, FileUploadEnum fileUploadEnum) {

        //判断文件是否符合该文件形式下的文件大小限制
        if (file == null || file.isEmpty()) {
            throw new MyException(ErrorConstant.File.FILE_IS_EMPTY);
        }
        if (file.getSize() > fileUploadEnum.getMaxSize()) {
            throw new MyException(ErrorConstant.File.FILE_SIZE_EXCEEDS);
        }

        // 获取文件名
        String fileName = UUID.randomUUID().toString() + file.getOriginalFilename();

        String basePath = fileUploadEnum.getUploadUrl();

        //判断该文件夹是否存在
        File uploadFile = new File(basePath);
        if (!uploadFile.exists()) {
            uploadFile.mkdirs();
        }

        //把file文件以指定文件名传到指定位置
        try {
            file.transferTo(new File(uploadFile, fileName));
        } catch (IOException e) {
            throw new MyException(ErrorConstant.File.FILE_UPLOAD_ERROR);
        }

        log.info("文件上传成功");

        return basePath + "/" + fileName;
    }

    /**
     * 把传来的markdown源码字符纯转为相应的html代码字符串
     * @param md
     * @return
     */
    public static String markdownToHtml(String md) {
        MutableDataSet options = new MutableDataSet();

        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();

        // You can re-use parser and renderer instances
        Node document = parser.parse(md);
        String html = renderer.render(document);  // "<p>This is <em>Sparta</em></p>\n"
        return html;
    }

    /**
     * 判断用户名是否符合要求
     * @param username
     * @return
     */
    public static boolean matchUsername(String username) {

        boolean flag = true;

        if (StringUtils.isEmpty(username)) {
            flag = false;
        } else if (username.length() > DefaultConstant.User.USER_NAME_MAX_LENGTH) {
            flag = false;
        } else if (!Pattern.matches("([a-z]|[A-Z]|[0-9]|[\\u4e00-\\u9fa5])+.*", username)) {
            flag = false;
        }

        return flag;
    }

    /**
     * 判断密码是否符合要求
     * @param password
     * @return
     */
    public static boolean matchPassword(String password) {
        return (password.length() >= 8 && password.length() <= 16);
    }

}