package com.swp.dataweb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.swp.dataweb.dao.FormItemMapper;
import com.swp.dataweb.dao.FormMapper;
import com.swp.dataweb.dao.ItemMapper;
import com.swp.dataweb.dao.TDataMapper;
import com.swp.dataweb.entity.Form;
import com.swp.dataweb.entity.FormItem;
import com.swp.dataweb.entity.query.FormQuery;
import com.swp.dataweb.entity.request.ItemSort;
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
    @Resource
    private FormItemMapper formItemMapper;


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
//            int i1 = formMapper.deleteRelation(form.getId());
//            if(i1>0){
//                int i2 = formMapper.addRelation(form);
//                if(i2>0)
            return SysResult.success();
//            }
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
    @Transactional(rollbackFor = Exception.class)
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
                new QueryWrapper<Form>().eq("subject_id", subjectId)
        );
        return SysResult.success(list);
    }

    @Transactional(rollbackFor = Exception.class)
    public SysResult updateFormItemSort(ItemSort itemSort) {
        /** 修改表单关联的itemId对应行的used即可(删除将used改为0) */
        int i = formMapper.updateRelation(itemSort);
        if (i > 0) {
            return SysResult.success();
        }
        return SysResult.error();
    }

    /**
     * 获取所有关联的问项id
     */
    public SysResult getItemIds(Long formId) {
        List<Long> itemIds = formItemMapper.getItemIds(formId);
        return SysResult.success(itemIds);
    }

    @Transactional(rollbackFor = Exception.class)
    public SysResult addFormItem(Form form) {
        /**
         * 添加的时候先判断是不是删除过的  也就是差关联表中是否存在该项与该项的used属性,
         * 如果used为0就改为1
         * 如果不存在该问项就添加关联
         */
        int insert = 0;
        FormItem formItem = formItemMapper.selectOne(
                new QueryWrapper<FormItem>()
                        .select("id", "used")
                        .eq("form_id", form.getId())
                        .eq("item_id", form.getItemIds().get(0))
        );
        if (formItem != null) {
            insert = formItemMapper.updateById(formItem.setUsed(true));
        } else {
            form.setCreator(Utils.getNickName());
            insert = formMapper.addRelation(form);
        }
        if (insert == 1) {
            return SysResult.success();
        }
        return SysResult.error();
    }

    @Transactional(rollbackFor = Exception.class)
    public SysResult deleteFormItem(Form form) {
        //修改关联表的used为0
        int i = formItemMapper.deleteRelation(form, form.getItemIds().get(0));
        if (i == 1) {
            return SysResult.success();
        }
        return SysResult.error();
    }
}
