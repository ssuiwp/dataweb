package com.swp.dataweb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("user_subject_type")
@ToString
@Accessors(chain = true)
public class UserSubjectType extends BaseEntity{
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    @NotBlank(message = "课题类型为空")
    private String subjectType;


}
