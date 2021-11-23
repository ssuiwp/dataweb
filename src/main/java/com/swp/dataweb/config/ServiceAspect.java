package com.swp.dataweb.config;

import com.swp.dataweb.utils.Utils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Configuration
@Aspect
public class ServiceAspect {

    private final String ExpGetResultDataPonit =
            "execution(public org.springframework.http.ResponseEntity org.springframework.security.oauth2.provider.endpoint.TokenEndpoint.postAccessToken*(..))";

    @Pointcut(ExpGetResultDataPonit)
    public void checkParam() {
    }

    @Around("checkParam()")
    public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //获取参数值
        Object[] paranValues = proceedingJoinPoint.getArgs();

        for (Object arg : paranValues) {
            if (arg instanceof Map) {
                Map<String, String> map = (Map) arg;
                String grant_type = map.get("grant_type");
                if ("password".equalsIgnoreCase(grant_type)) {
                    String password = map.get("password");
                    String s = Utils.checkAndGetPassword(password);
                    map.put("password", s);
                }
            }
        }
        return proceedingJoinPoint.proceed();
    }
}