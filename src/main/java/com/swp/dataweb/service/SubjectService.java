package com.swp.dataweb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.swp.dataweb.dao.*;
import com.swp.dataweb.entity.Subject;
import com.swp.dataweb.entity.SubjectPartnerUser;
import com.swp.dataweb.entity.User;
import com.swp.dataweb.entity.query.SubjectQuery;
import com.swp.dataweb.entity.UserSubjectType;
import com.swp.dataweb.entity.request.SubjectPartner;
import com.swp.dataweb.entity.response.PageResult;
import com.swp.dataweb.entity.response.Status;
import com.swp.dataweb.entity.response.SysResult;
import com.swp.dataweb.exception.SuRuntimeException;
import com.swp.dataweb.utils.Utils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
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
    @Resource
    private TDataMapper dataMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private SubjectPartnerUserMapper subjectPartnerUserMapper;

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
        List<Subject> subjects = subjectMapper.getSubjects(query, start, Utils.getUserId());
        //查询合作的课题
        List<Subject> partnerLists = subjectMapper.selectPartnerSubject(Utils.getUserId());
        subjects.addAll(partnerLists);
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
            dataMapper.deleteTData(formId);
            formMapper.deleteForm(formId);
        }
        subjectMapper.deleteSubject(id);
        return SysResult.success(Status.SUCCESS);
    }

    /**
     * 课题类型
     *
     * @param type
     * @return
     */
    @Transactional
    public boolean addType(UserSubjectType type) {
        type.setUserId(Utils.getUserId());
        int row = subjectTypeMapper.updateOrInsert(type);
        return row > 0;
    }

    public PageResult findType(PageResult type) {
        type.setTotal((long) subjectTypeMapper.selectAllCount(Utils.getUserId()));
        int start = type.getSize() * (type.getCurrent() - 1);
        List<UserSubjectType> list =
                subjectTypeMapper.findType(type, start, Utils.getUserId());
        type.setRaws(list);
        return type;
    }

    @Transactional
    public boolean deleteType(long id) {
        int i = subjectTypeMapper.deleteById(id);
        return i == 1;
    }

    public SysResult findIdAndName() {
        List<Subject> subjects = subjectMapper.selectList(
                new QueryWrapper<Subject>().eq("user_id", Utils.getUserId())
        );
        List<Subject> selectPartnerSubject =
                subjectMapper.selectPartnerSubject(Utils.getUserId());
        subjects.addAll(selectPartnerSubject);
        return SysResult.success(subjects);
    }

    public PageResult findAllType(PageResult type) {
        List<UserSubjectType> list =
                subjectTypeMapper.selectList(
                        new QueryWrapper<UserSubjectType>()
                                .eq("user_id", Utils.getUserId()));
        type.setRaws(list);
        return type;
    }

    public boolean addPartner(SubjectPartner partner) {
        String partnerUsername = partner.getPartnerUsername();
        //查询合伙人user
        User partnerUser = userMapper.selectOne(
                new QueryWrapper<User>().select("id").eq("username", partnerUsername)
        );
        Subject isMy = subjectMapper.selectById(partner.getSubjectId());
        if (!isMy.getUserId().equals(Utils.getUserId())) {
            throw new SuRuntimeException(Status.NOT_MY_SUBJECT);
        }
        if (partnerUser == null) {
            throw new SuRuntimeException(Status.USER_NAME_EMPTY);
        }
        //判断是否重复添加
        SubjectPartnerUser subjectPartnerUserOne = subjectPartnerUserMapper.selectOne(
                new QueryWrapper<SubjectPartnerUser>()
                        .eq("user_id", partnerUser.getId())
                        .eq("subject_id", partner.getSubjectId())
        );
        if (subjectPartnerUserOne != null) {
            throw new SuRuntimeException(Status.SUBJECT_PARTNER_REPEAT);
        }
        //构建dto
        SubjectPartnerUser subjectPartnerUser =
                new SubjectPartnerUser(null, partnerUser.getId(), partner.getSubjectId());
        //给合伙人添加课题
        int insert = subjectPartnerUserMapper.insert(subjectPartnerUser);
        return insert == 1;
    }

    public SysResult findPartner(Long subjectId) {
        //获取所有的合伙人用户信息(用户id,用户名,昵称)
        List<User> list = userMapper.selectPartnerSubject(subjectId);
        List<SubjectPartner> partners = new ArrayList<>();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                User user = list.get(i);
                if (Utils.getUserId().equals(user.getId())) {
                    list.remove(i);
                    --i;
                    continue;
                }
                partners.add(new SubjectPartner(
                        subjectId, user.getUsername(), user.getNickname(), user.getId()));
            }
        }
        return SysResult.success(partners);
    }

    public boolean removePartner(SubjectPartner partner) {
        int delete = subjectPartnerUserMapper.delete(
                new QueryWrapper<SubjectPartnerUser>()
                        .eq("user_id", partner.getPartnerId())
                        .eq("subject_id", partner.getSubjectId())
        );
        return delete == 1;
    }
}
