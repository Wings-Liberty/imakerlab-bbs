package cn.imakerlab.bbs.security.aop;

import cn.imakerlab.bbs.security.MyResponseEntity;
import cn.imakerlab.bbs.utils.ResultUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Aspect
@Configuration
public class getTokenAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Around("execution(public * org.springframework.security.oauth2.provider.endpoint.TokenEndpoint.postAccessToken(..))")
    public Object method(ProceedingJoinPoint pjp) throws Throwable {

        ResponseEntity result = (ResponseEntity) pjp.proceed();

        Object body = result.getBody();

        return new MyResponseEntity().setBody(
                ResultUtils.success().setCode(HttpStatus.OK.value()).setMsg("成功了").setData(body)
        );

    }

}