package com.swp.dataweb.service;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.swp.dataweb.dao.SubjectTypeMapper;
import com.swp.dataweb.entity.UserSubjectType;
import com.swp.dataweb.utils.Utils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SubjectTypeService {
    @Resource
    private SubjectTypeMapper subjectTypeMapper;

    /**  添加课题类型*/
    public boolean add(UserSubjectType userSubjectType) {
        userSubjectType.setUserId(Utils.getUserId());
        int row = subjectTypeMapper.insert(userSubjectType);
        return row == 1;
    }
    /** 获取所有与课题类型*/
    public List<UserSubjectType> getSubjectType(String subjectType) {
        QueryWrapper<UserSubjectType> query = new QueryWrapper();
        query.eq("user_id",Utils.getUserId())
                .like(!StringUtils.isEmpty(subjectType),"subject_type",subjectType);
        return subjectTypeMapper.selectList(query);
    }
    /** 修改课题类型*/
    public boolean update(UserSubjectType userSubjectType) {
        int i = subjectTypeMapper.updateById(userSubjectType);
        return i==1;
    }
}
