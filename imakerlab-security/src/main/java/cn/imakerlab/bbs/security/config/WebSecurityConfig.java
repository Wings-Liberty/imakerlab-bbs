package cn.imakerlab.bbs.security.config;

import cn.imakerlab.bbs.security.JwtTokenEnhancer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //获取JwtTokenStore
    @ConditionalOnProperty(prefix = "bbs.security.oauth2", name = "storeType", havingValue = "jwt", matchIfMissing = true)
    @Bean
    public TokenStore getJwtTokenStore(){
        JwtTokenStore jwtTokenStore = new JwtTokenStore(getJwtAccessTokenConverter());
        return jwtTokenStore;
    }

    //用来添加签名的类，增强器的一种
    @Bean
    public JwtAccessTokenConverter getJwtAccessTokenConverter(){
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        //设置自定义的签名
        jwtAccessTokenConverter.setSigningKey("cx");

        return jwtAccessTokenConverter;
    }

    //增强器，这里是用来给jwt添加自定义属性
    @Bean
    public TokenEnhancer getTokenEnhancer(){
        return new JwtTokenEnhancer();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
