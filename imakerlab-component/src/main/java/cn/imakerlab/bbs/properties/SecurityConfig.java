package cn.imakerlab.bbs.properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 这个类是用来让ConfigProperties里的配置生效的
 * 如果没有这个类，那么ConfigProperties将无法生效
 */
@Configuration
@EnableConfigurationProperties(ConfigProperties.class)
public class SecurityConfig {
}
