package com.swp.dataweb.entity.Response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class Response<T> {

    private int code;

    private String msg;

    private T data;

    private Response(int status, String msg) {
        this.code = status;
        this.msg = msg;
    }

    private Response(Status status) {
        if (status != null) {
            this.code = status.getCode();
            this.msg = status.getDesc();
        }
    }

    private Response(T data) {
        this.code = Status.SUCCESS.getCode();
        this.msg = Status.SUCCESS.getDesc();
        this.data = data;
    }

    private Response(Status status, T data) {
        this.code = status.getCode();
        this.msg = status.getDesc();
        this.data = data;
    }


    @JsonCreator
    private Response(@JsonProperty("code") int code,
                     @JsonProperty("msg") String msg,
                     @JsonProperty("data") T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Response<T> success(Status status, T data){
        return new Response<>(status,data);
    }

    public static <T> Response<T> success(T data) {
        return new Response<T>(data);
    }
    public static <T> Response<T> error(Status status){
        return new Response<T>(status);
    }
}


