package com.swp.dataweb.entity.response;

import com.swp.dataweb.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class PageResult implements Serializable {
    private String query;
    private Integer size;
    private Integer current;
    private Long total;//总记录数
    private Object raws;//分页数据
}
