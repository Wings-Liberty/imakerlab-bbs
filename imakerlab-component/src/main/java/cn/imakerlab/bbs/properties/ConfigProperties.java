package cn.imakerlab.bbs.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 这个类会从application配置文件中读取以imakerlab.bbs开头的值到类的变量中
 */
@Component
@ConfigurationProperties(prefix = "imakerlab.bbs")
public class ConfigProperties {

    private FileProperties file = new FileProperties();


    public FileProperties getFile() {
        return file;
    }

    public void setFile(FileProperties file) {
        this.file = file;
    }
}