package com.swp.dataweb.handler;

import com.swp.dataweb.entity.response.SysResult;
import com.swp.dataweb.exception.SuRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 异常类型:  自定义运行时异常
     */
    @ExceptionHandler(value = SuRuntimeException.class)
    public SysResult fail(SuRuntimeException e) {
        //返回异常结果
        log.error(e.getMessage(), e);
        return SysResult.error(e.getStatus(),e.getMessage(), e.getData());
    }


    /**
     * 异常类型:  意外异常
     */
    @ExceptionHandler(value = Exception.class)
    public SysResult error(Exception e) {
        log.error(e.getMessage(), e);
        //输出异常
        return SysResult.error();
    }

}
