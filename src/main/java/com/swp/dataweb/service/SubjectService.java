package com.swp.dataweb.service;

import com.swp.dataweb.dao.FormMapper;
import com.swp.dataweb.dao.SubjectMapper;
import com.swp.dataweb.entity.Subject;
import com.swp.dataweb.entity.SubjectQuery;
import com.swp.dataweb.entity.User;
import com.swp.dataweb.entity.response.Status;
import com.swp.dataweb.entity.response.SysResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 *
 */
@Service
public class SubjectService {
    @Resource
    private SubjectMapper subjectMapper;
    @Resource
    private FormMapper formMapper;

    public SysResult<Subject> createSubject(/*User user,*/Subject subject) {
/*        SysResult<Subject> SysResult = checkSubject(user,subject);
        if (SysResult != null) {
            return SysResult;
        }*/
        subjectMapper.addSubject(subject);
        return SysResult.success(subject);
    }

    @Transactional
    public SysResult obtainSubject(SubjectQuery query){
        List<Subject> subjects = subjectMapper.getSubjects(query);
//        PageInfo p = new PageInfo();
//        p.setTotal(subjectMapper.getTotal());
        return SysResult.success(Status.SUCCESS,null);
    }

    public SysResult<Subject> updateSubject(Subject subject){
        subjectMapper.updateSubject(subject);
        return SysResult.success(subject);
    }

    @Transactional
    public SysResult deleteSubject(Subject subject){
        List<Long> formIds = formMapper.getFormId(subject.getId());
        for (Long formId : formIds) {
            formMapper.deleteRelation(formId);
            formMapper.deleteForm(formId);
        }
        subjectMapper.deleteSubject(subject.getId());
        return SysResult.success(Status.SUCCESS);
    }

    private static SysResult<Subject> checkSubject(User user, Subject subject){
        if(isEmpty(subject.getName())){
            return SysResult.error(Status.SUBJECT_NAME_EMPTY);
        }
       /* if(isEmpty(subject.getOwner())){
            return SysResult.error(Status.SUBJECT_UNIT_EMPTY);
        }*/
        subject.setOwner(user.getUsername());
        return null;
    }


}
