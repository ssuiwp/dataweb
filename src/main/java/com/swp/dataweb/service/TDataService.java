package com.swp.dataweb.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swp.dataweb.dao.FormMapper;
import com.swp.dataweb.dao.SubjectMapper;
import com.swp.dataweb.dao.TDataMapper;
import com.swp.dataweb.entity.Form;
import com.swp.dataweb.entity.Subject;
import com.swp.dataweb.entity.response.SysResult;
import com.swp.dataweb.entity.TData;
import com.swp.dataweb.entity.TDataModel;
import com.swp.dataweb.utils.Utils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TDataService {

    @Resource
    private TDataMapper tDataMapper;
    @Resource
    private SubjectMapper subjectMapper;
    @Resource
    private FormMapper formMapper;

    public SysResult createTData(TDataModel tDataModel){
        long formId = tDataModel.getSubjectForm()[1];
        String s = JSON.toJSON(tDataModel.getTData()).toString();
        System.out.println(s);
        TData data = new TData()
                .setData(s)
                .setCreator(Utils.getNickName())
                .setFormId(formId);
        tDataMapper.addTData(data, Utils.getNickName());
        return SysResult.success();
    }


    public SysResult obtainTData(long formId){

        TData data = tDataMapper.getTData(formId);
        return SysResult.success(data);
    }


    public SysResult updateTData(TDataModel tDataModel){
        long formId = tDataModel.getSubjectForm()[1];
        String s = JSON.toJSON(tDataModel.getTData()).toString();
        TData data = new TData()
                .setData(s)
                .setCreator(Utils.getNickName())
                .setFormId(formId);
        tDataMapper.update(data,
                new QueryWrapper<TData>().eq("form_id",formId));
        return SysResult.success();
    }


    public SysResult getSubjectAndFormName() {
        List<Subject> subjects = subjectMapper.selectList(
                new QueryWrapper<Subject>().eq("user_id",Utils.getUserId())
        );
        for (Subject subject : subjects) {
            subject.setChildren(formMapper.selectList(
                    new QueryWrapper<Form>().eq("subject_id",subject.getId())
            ));
        }
        return SysResult.success(subjects);
    }
}
