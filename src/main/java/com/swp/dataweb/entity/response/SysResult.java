package com.swp.dataweb.entity.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;


@Data
public class SysResult<T> implements Serializable {

    private int code;

    private String msg;

    private T data;

    private SysResult(int status, String msg) {
        this.code = status;
        this.msg = msg;
    }

    private SysResult(Status status) {
        if (status != null) {
            this.code = status.getCode();
            this.msg = status.getDesc();
        }
    }

    private SysResult(T data) {
        this.code = Status.SUCCESS.getCode();
        this.msg = Status.SUCCESS.getDesc();
        this.data = data;
    }

    private SysResult(Status status, T data) {
        this.code = status.getCode();
        this.msg = status.getDesc();
        this.data = data;
    }



    @JsonCreator
    private SysResult(@JsonProperty("code") int code,
                     @JsonProperty("msg") String msg,
                     @JsonProperty("data") T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> SysResult<T> success(Status status, T data){
        return new SysResult<>(status,data);
    }
    public static <T> SysResult<T> success(){
        return new SysResult<>(Status.SUCCESS);
    }

    public static <T> SysResult<T> success(T data) {
        return new SysResult<T>(data);
    }
    public static <T> SysResult<T> error(Status status){
        return new SysResult<T>(status);
    }
    public static <T> SysResult<T> error(){
        return new SysResult<T>(Status.FAILURE);
    }
    public static <T> SysResult<T> error(Status status,T data){
        return new SysResult<T>(Status.FAILURE,data);
    }
    public static <T> SysResult<T> error(Status status,String msg,T data){
        return new SysResult<T>(status.getCode(),msg,data);
    }

}


