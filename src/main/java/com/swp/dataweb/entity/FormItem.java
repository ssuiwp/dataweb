package com.swp.dataweb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("form_item")
@Accessors(chain = true)
public class FormItem extends BaseEntity {

    //表单id
    private Long formId;

    //问项id
    private Long itemId;

    //登记人
    private String creator;
//    关联表id
    @TableId(type = IdType.AUTO)
    private Long id;

    private boolean used;

}
