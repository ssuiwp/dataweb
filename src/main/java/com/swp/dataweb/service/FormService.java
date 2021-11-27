package com.swp.dataweb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.swp.dataweb.dao.FormMapper;
import com.swp.dataweb.dao.TDataMapper;
import com.swp.dataweb.entity.Form;
import com.swp.dataweb.entity.query.FormQuery;
import com.swp.dataweb.entity.response.PageResult;
import com.swp.dataweb.entity.response.Status;
import com.swp.dataweb.entity.response.SysResult;
import com.swp.dataweb.utils.Utils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class FormService {
    @Resource
    private FormMapper formMapper;
    @Resource
    private TDataMapper tDataMapper;


    /**
     * 创建表单和表单与问项关联表
     *
     * @param form 表单
     */
    @Transactional(rollbackFor = Exception.class)
    public SysResult createForm(Form form) throws Exception {
        form.setCreator(Utils.getNickName());
        int i = formMapper.insert(form);
        if (i == 1) {
            int j = formMapper.addRelation(form);
            if (j > 0) {
                return SysResult.success();
            }
        }
        throw new Exception("表单添加失败");
    }

    /**
     * 更新表单
     */
    @Transactional(rollbackFor = Exception.class)
    public SysResult updateForm(Form form) throws Exception {
        int i = formMapper.updateById(form);
        if (i == 1) {
            int i1 = formMapper.deleteRelation(form.getId());
            if(i1>0){
                int i2 = formMapper.addRelation(form);
                if(i2>0)return SysResult.success();
            }
        }
        throw new Exception("表单更新失败");
    }


    /**
     * 查找表单
     */
    @Transactional
    public SysResult obtainForm(FormQuery query) {
        PageResult page = new PageResult();
        page.setTotal((long) formMapper.getTotal(query.getSubjectId()));
        long start = query.getSize() * (query.getCurrent() - 1);
        List<Form> forms = formMapper
                .getForms(query.getFormName(), start, query.getSubjectId(), query.getSize());
        page.setRaws(forms);
        return SysResult.success(Status.SUCCESS, page);
    }

    /**
     * 删除表单
     */
    @Transactional
    public SysResult deleteForm(Form form) {
        if (form != null) {
            int i2 = formMapper.deleteForm(form.getId());
            int i1 = formMapper.deleteRelation(form.getId());
            int i = tDataMapper.deleteTData(form.getId());
            return SysResult.success(Status.SUCCESS);
        }
        return SysResult.error(Status.FAILURE);
    }

    public SysResult findAll(Long subjectId) {
        List<Form> list = formMapper.selectList(
                new QueryWrapper<Form>().eq("subject_id",subjectId)
        );
        return SysResult.success(list);
    }
}
