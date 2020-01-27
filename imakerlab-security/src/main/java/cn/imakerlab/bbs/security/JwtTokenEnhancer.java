package cn.imakerlab.bbs.security;

import cn.imakerlab.bbs.mapper.UserDao;
import cn.imakerlab.bbs.model.po.User;
import cn.imakerlab.bbs.model.po.UserExample;
import cn.imakerlab.bbs.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class JwtTokenEnhancer implements TokenEnhancer {

    @Autowired
    UserDao userDao;

    //把自定义的信息添加到jwt
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication oAuth2Authentication) {

        Map<String, Object> info = new HashMap<>();

        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(oAuth2Authentication.getUserAuthentication().getName());
        User user = MyUtils.ListToOne(userDao.selectByExample(example));

        System.out.println("JwtTokenEnhancer查到的user\n" + user);

        info.put("organization", "imakerlab");
        info.put("userId", user.getId());

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);

        return accessToken;

    }


}