package com.swp.dataweb.handler;

import com.swp.dataweb.entity.response.Status;
import com.swp.dataweb.entity.response.SysResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author suiwp on 2020/9/5
 */
@Slf4j
@RestControllerAdvice
public class ValidatedExceptionHandler {

    /**
     * 处理@Validated参数校验失败异常
     * @param exception 异常类
     * @return 响应
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public SysResult exceptionHandler(MethodArgumentNotValidException exception){
        BindingResult result = exception.getBindingResult();
        StringBuilder stringBuilder = new StringBuilder();
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            if (errors != null&&!errors.isEmpty()) {
                errors.forEach(p -> {
                    FieldError fieldError = (FieldError) p;
                    log.warn("Bad Request Parameters: dto entity [{}],field [{}],message [{}]",fieldError.getObjectName(), fieldError.getField(), fieldError.getDefaultMessage());
                    stringBuilder.append(fieldError.getDefaultMessage());
                });
            }
        }
        return SysResult.error(Status.VALIDATED_ERROR,stringBuilder.toString(),null);
    }
}