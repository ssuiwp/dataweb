package com.swp.dataweb.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemSort implements Serializable {

    /**
     * 需要修改的课题id
     */
    private Long formId;

    /**
     * 需要修改的问项id
     */
    private Long id;

    /**
     * 需要修改的item对应的顺序
     */
    private Long orderNum;

    /**
     * itemsorts
     */
    private List<ItemSort> itemSorts;
}
