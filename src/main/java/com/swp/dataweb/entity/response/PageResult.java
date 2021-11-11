package com.swp.dataweb.entity.response;

import com.swp.dataweb.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class PageResult extends BaseEntity {

    private Integer total;//总记录数
    private Integer pageNum;//当前页码
    private Integer size;//每页显示条数
    private Object raws;//分页数据
    private Integer start = (pageNum-1)*size;//起始行数
}
