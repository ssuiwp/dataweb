package com.swp.dataweb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.swp.dataweb.dao.FormMapper;
import com.swp.dataweb.dao.SubjectMapper;
import com.swp.dataweb.dao.SubjectTypeMapper;
import com.swp.dataweb.entity.Subject;
import com.swp.dataweb.entity.SubjectQuery;
import com.swp.dataweb.entity.UserSubjectType;
import com.swp.dataweb.entity.response.PageResult;
import com.swp.dataweb.entity.response.Status;
import com.swp.dataweb.entity.response.SysResult;
import com.swp.dataweb.utils.Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 */
@Service
public class SubjectService {
    @Resource
    private SubjectMapper subjectMapper;
    @Resource
    private FormMapper formMapper;
    @Resource
    private SubjectTypeMapper subjectTypeMapper;

    @Transactional
    public boolean createSubject(/*User user,*/Subject subject) {
        subject.setUserId(Utils.getUserId());
        subject.setOwner(Utils.getNickName());
        return subjectMapper.insert(subject) == 1;
    }

    public SysResult obtainSubject(SubjectQuery query) {
        PageResult page = new PageResult();
        page.setTotal((long) subjectMapper.getTotal(Utils.getUserId()));
        long start = query.getSize() * (query.getCurrent() - 1);
        List<Subject> subjects = subjectMapper.getSubjects(query, start,Utils.getUserId());
        page.setRaws(subjects);
        return SysResult.success(Status.SUCCESS, page);
    }

    @Transactional
    public SysResult<Subject> updateSubject(Subject subject) {
        int i = subjectMapper.updateSubject(subject);
        if (i == 1) {
            return SysResult.success(subject);
        }
        return SysResult.error();
    }

    @Transactional
    public SysResult deleteSubject(long id) {
        List<Long> formIds = formMapper.getFormId(id);
        for (Long formId : formIds) {
            formMapper.deleteRelation(formId);
            formMapper.deleteForm(formId);
        }
        subjectMapper.deleteSubject(id);
        return SysResult.success(Status.SUCCESS);
    }

    /**
     * 课题类型
     * @param type
     * @return
     */
    @Transactional
    public boolean addType(UserSubjectType type) {
        type.setUserId(Utils.getUserId());
        int row = subjectTypeMapper.updateOrInsert(type);
        return row>0;
    }

    public PageResult findType(PageResult type) {
        type.setTotal((long) subjectTypeMapper.selectAllCount(Utils.getUserId()));
        int start = type.getSize() * (type.getCurrent() - 1);
        List<UserSubjectType> list =
                subjectTypeMapper.findType(type, start,Utils.getUserId());
        type.setRaws(list);
        return type;
    }
    @Transactional
    public boolean deleteType(long id) {
        int i = subjectTypeMapper.deleteById(id);
        return i==1;
    }
}
