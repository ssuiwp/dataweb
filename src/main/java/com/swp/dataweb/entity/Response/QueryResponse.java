package com.swp.dataweb.entity.Response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.swp.dataweb.entity.PageInfo;
import lombok.Data;

@Data
public class QueryResponse<T> {
    private int code;
    private String msg;
    private T data;
    private PageInfo pageInfo;

    private QueryResponse(int status, String msg){
        this.code = status;
        this.msg = msg;
    }

    private QueryResponse(Status status) {
        if (status != null) {
            this.code = status.getCode();
            this.msg = status.getDesc();
        }
    }
    private QueryResponse(T data){
        this.code = Status.SUCCESS.getCode();
        this.msg = Status.SUCCESS.getDesc();
        this.data = data;
    }
    private QueryResponse(Status status, T data){
        this.code = status.getCode();
        this.msg = status.getDesc();
        this.data = data;
    }
    private QueryResponse(Status status, T data, PageInfo pageInfo){
        this.code = status.getCode();
        this.msg = status.getDesc();
        this.data = data;
        this.pageInfo = pageInfo;
    }
    private QueryResponse(T data, PageInfo pageInfo){
        this.data = data;
        this.pageInfo = pageInfo;
    }
    @JsonCreator
    private QueryResponse(@JsonProperty("code") int code,
                          @JsonProperty("msg") String msg,
                          @JsonProperty("data") T data,
                          @JsonProperty("pageBean") PageInfo pageInfo){
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.pageInfo = pageInfo;
    }
    public static <T> QueryResponse<T> success(Status status, T data, PageInfo pageInfo){
        return new QueryResponse<T>(status,data,pageInfo);
    }

    public static <T> QueryResponse<T> success(T data, PageInfo pageInfo) {
        return new QueryResponse<T>(data,pageInfo);
    }
    public static <T> QueryResponse<T> error(Status status){
        return new QueryResponse<T>(status);
    }

}
