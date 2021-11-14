package com.swp.dataweb.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

@Data
public class BaseEntity implements Serializable {
    @TableField(exist = false)
    private String created;
    @TableField(exist = false)
    private String updated;
}
