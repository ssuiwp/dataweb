package com.swp.dataweb.entity.Request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddRequest<T> implements Request {
    private T entity;

    public AddRequest(T entity){

        this.entity = entity;

        //todo

    }

}
