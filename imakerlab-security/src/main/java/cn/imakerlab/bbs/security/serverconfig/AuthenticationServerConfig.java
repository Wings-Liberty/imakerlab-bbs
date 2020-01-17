package cn.imakerlab.bbs.security.serverconfig;

import cn.imakerlab.bbs.security.exeception.MyExeceptionTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * 认证服务器
 */
@Configuration
@EnableAuthorizationServer
public class AuthenticationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    MyExeceptionTranslator myExeceptionTranslator;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Qualifier("myUserDetailsService")
    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Qualifier("getJwtAccessTokenConverter")
    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Qualifier("getTokenEnhancer")
    @Autowired
    private TokenEnhancer jwtTokenEnhancer;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .userDetailsService(userDetailsService)
                .authenticationManager(authenticationManager);

        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();

        List<TokenEnhancer> list = new ArrayList<>();

        list.add(jwtAccessTokenConverter);
        list.add(jwtTokenEnhancer);

        tokenEnhancerChain.setTokenEnhancers(list);

        endpoints
                .tokenEnhancer(tokenEnhancerChain);

        endpoints.exceptionTranslator(myExeceptionTranslator);

    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        clients.inMemory()
                .withClient("imaker") //配置client-id
                .secret(passwordEncoder.encode("imaker")) //配置client-secret
                .authorizedGrantTypes("refresh_token", "password") //设置申请token的方式（只能从默认的五种方式里选），此后只能使用设定的方式申请token
                .accessTokenValiditySeconds(3660) //设置令牌有效期3600秒
                .refreshTokenValiditySeconds(3600 * 24) //设置刷新令牌的有效期是一天
                .scopes("all", "read", "write"); //设置用户能获得的权限都有哪些

    }

}