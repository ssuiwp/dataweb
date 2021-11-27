package com.swp.dataweb.exception;

import com.swp.dataweb.entity.response.Status;

public class SuRuntimeException extends RuntimeException{
    private final Status status;
    private Object data;
    public SuRuntimeException(Status status) {
        super(status.getDesc());
        this.status = status;
    }
    public SuRuntimeException(Status status,Object data) {
        super(status.getDesc());
        this.status = status;
        this.data = data;
    }
    public Status getStatus(){
        return this.status;
    }
    public Object getData(){
        return this.data;
    }
}
