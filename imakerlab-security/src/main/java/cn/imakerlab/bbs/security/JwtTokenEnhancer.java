package cn.imakerlab.bbs.security;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class JwtTokenEnhancer implements TokenEnhancer {

    //把自定义的信息添加到jwt
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication oAuth2Authentication) {

        Map<String, Object> info = new HashMap<>();

        info.put("organization", "imaker");

        ((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(info);

        return accessToken;

    }
}