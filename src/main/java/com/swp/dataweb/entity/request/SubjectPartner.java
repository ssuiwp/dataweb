package com.swp.dataweb.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectPartner implements Serializable {
    /** 需要添加合伙人的课题id */
    private Long subjectId;
    /** 需要添加的合伙人用户名 */
    private String partnerUsername;
    //返回的合伙人的昵称
    private String partnerNickname;
    /**
     * 合伙人的id
     */
    private Long partnerId;
}
