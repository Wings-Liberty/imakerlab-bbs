package cn.imakerlab.bbs.test;

import cn.imakerlab.bbs.properties.ConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;

public class test {




    public static void main(String[] args) {
        ConfigProperties configProperties = new ConfigProperties();

        System.out.println(configProperties.getFile().getFigureFolderUrl());
    }

}