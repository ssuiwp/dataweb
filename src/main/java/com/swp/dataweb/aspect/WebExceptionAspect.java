package com.swp.dataweb.aspect;

import com.swp.dataweb.entity.response.SysResult;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class WebExceptionAspect {
    @Pointcut("@annotation(org.springframework.stereotype.Service)")
    private void webPointCut(){}

    @AfterThrowing(pointcut= "webPointCut()",throwing = "e")
    public SysResult afterThrowable(JoinPoint joinPoint, Throwable e) {
        return SysResult.error();
    }


}
