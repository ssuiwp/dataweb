package com.swp.dataweb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("data")
@Accessors(chain = true)
public class TData extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField(exist = false)
    private Form form;
    @TableField(exist = false)
    private Item item;
    private Long formId;
    private String data;
    private String creator;

}
