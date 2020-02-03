package cn.imakerlab.bbs.security.exeception;

import cn.imakerlab.bbs.security.MyResponseEntity;
import cn.imakerlab.bbs.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

/**
 * Spring Security Oauth2 自定义 处理Exception
 */
@Component
public class MyExeceptionTranslator implements WebResponseExceptionTranslator {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public ResponseEntity translate(Exception e) throws Exception {

        logger.info(e.getMessage());

        return new MyResponseEntity().setBody(
                ResultUtils.failure(100).setMsg("用户名或密码错误")
        );

    }
}