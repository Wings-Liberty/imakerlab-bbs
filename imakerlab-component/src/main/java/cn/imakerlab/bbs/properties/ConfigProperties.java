package cn.imakerlab.bbs.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

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