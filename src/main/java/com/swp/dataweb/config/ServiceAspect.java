package com.swp.dataweb.config;

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
//            System.out.println("ARG原来为:" + arg);
            if (arg instanceof Map) {
                Map<String, String> map = (Map) arg;
                String password = map.get("password");
                if (password != null) {
                    String[] chars = password.split("\\s");
                    StringBuilder p = new StringBuilder();
                    for (String aChar : chars) {
                        p.append(String.valueOf((char) Integer.parseInt(aChar)));
                    }
                    int salt1 = Integer.parseInt(String.valueOf(p.toString().charAt(0)));
                    String q = p.substring(1);
                    String date = new SimpleDateFormat("hhmm").format(new Date());
                    boolean equals = date.equals(q.substring(salt1, salt1 + 4));
                    if(equals) {
                        String s2 = q.substring(0, salt1) + p.substring(salt1 + 5);
                        map.put("password", s2);
                    }else{
                        map.put("password", "");
                    }

                }
            }
        }

        return proceedingJoinPoint.proceed();

    }


}