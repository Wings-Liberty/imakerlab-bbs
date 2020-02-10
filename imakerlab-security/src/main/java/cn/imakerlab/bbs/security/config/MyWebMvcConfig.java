package cn.imakerlab.bbs.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry
//                .addResourceHandler("/uploadfile/**")
//                .addResourceLocations("D:/IDEA-workspace/imakerlab-bbs/imakerlab-web/src/main/resources/static/uploadfile/");
//                    .addResourceLocations("/webapp/uploadfile/");

    }
}
