package com.swp.dataweb.entity.query;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemQuery implements Serializable {

    //问项id
    private Long id;

    //问项名称
    @JsonProperty("name")
    private String itemName;

    //问项类型
    private String type;

    private int size;

    private int current;
    //点击页面直接根据用户id查询所有问项
    private Long userId;
}
