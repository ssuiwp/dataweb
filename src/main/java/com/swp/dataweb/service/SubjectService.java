package com.swp.dataweb.service;

import com.swp.dataweb.dao.FormMapper;
import com.swp.dataweb.dao.SubjectMapper;
import com.swp.dataweb.entity.PageInfo;
import com.swp.dataweb.entity.Response.QueryResponse;
import com.swp.dataweb.entity.Response.Response;
import com.swp.dataweb.entity.Response.Status;
import com.swp.dataweb.entity.Subject;
import com.swp.dataweb.entity.SubjectQuery;
import com.swp.dataweb.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.apache.commons.lang3.StringUtils.isEmpty;

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

    public Response<Subject> createSubject(/*User user,*/Subject subject) {
/*        Response<Subject> response = checkSubject(user,subject);
        if (response != null) {
            return response;
        }*/
        subjectMapper.addSubject(subject);
        return Response.success(subject);
    }

    @Transactional
    public QueryResponse<List<Subject>> obtainSubject(SubjectQuery query){
        List<Subject> subjects = subjectMapper.getSubjects(query);
        PageInfo p = new PageInfo();
        p.setTotalCount(subjectMapper.getTotal());
        return QueryResponse.success(subjects,p);
    }

    public Response<Subject> updateSubject(Subject subject){
        subjectMapper.updateSubject(subject);
        return Response.success(subject);
    }

    @Transactional
    public Response deleteSubject(Subject subject){
        List<Long> formIds = formMapper.getFormId(subject.getId());
        for (Long formId : formIds) {
            formMapper.deleteRelation(formId);
            formMapper.deleteForm(formId);
        }
        subjectMapper.deleteSubject(subject.getId());
        return Response.success(Status.SUCCESS);
    }

    private static Response<Subject> checkSubject(User user, Subject subject){
        if(isEmpty(subject.getName())){
            return Response.error(Status.SUBJECT_NAME_EMPTY);
        }
       /* if(isEmpty(subject.getOwner())){
            return Response.error(Status.SUBJECT_UNIT_EMPTY);
        }*/
        subject.setOwner(user.getName());
        return null;
    }


}
