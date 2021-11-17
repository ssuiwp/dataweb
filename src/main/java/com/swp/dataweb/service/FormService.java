package com.swp.dataweb.service;

import com.swp.dataweb.dao.FormMapper;
import com.swp.dataweb.dao.TDataMapper;
import com.swp.dataweb.entity.Form;
import com.swp.dataweb.entity.query.FormQuery;
import com.swp.dataweb.entity.User;
import com.swp.dataweb.entity.response.Status;
import com.swp.dataweb.entity.response.SysResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isEmpty;

@Service
public class FormService {
    @Resource
    private FormMapper formMapper;
    @Resource
    private TDataMapper tDataMapper;



    /**
     * 创建表单和表单与问项关联表
     * @param form 表单
     */
    @Transactional
    public SysResult<Form> createForm(Form form) {
//        SysResult<Form> SysResult = checkForm(form);
//        if (SysResult != null) {
//            return SysResult;
//        }
        formMapper.addRelation(form);
        formMapper.addForm(form);
        return SysResult.success(form);
    }

    /**
     * 更新表单
     */
    @Transactional
    public SysResult<Form> updateForm(Form form){


        formMapper.updateRelation(form);
        formMapper.updateForm(form);
        return SysResult.success(form);
    }


    /**
     * 查找表单
     */
    @Transactional
    public SysResult obtainForm(FormQuery query){
        List<Form> forms = formMapper.getForm(query);
//        PageInfo p = new PageInfo();
        int total = formMapper.getTotal();
//        p.setTotal(total);
        return SysResult.success(Status.SUCCESS,null);
    }

    /**
     * 检测表单
     * @param user 用户
     * @param form 表单
     * @return 如果没有返回null
     */
    public static SysResult<Form> checkForm(User user, Form form){
        if(isEmpty(form.getName())){
            return SysResult.error(Status.FORM_NAME_EMPTY);
        }
        if(isEmpty(form.getSubject().getSubjectName())){
            return SysResult.error(Status.FORM_SUBJECT_EMPTY);
        }
        form.setCreator(user.getUsername());
        return null;
    }

    /**
     * 删除表单
     */
    @Transactional
    public SysResult deleteForm(Form form){
        if(form != null){
            int i2 = formMapper.deleteForm(form.getId());
            int i1 = formMapper.deleteRelation(form.getId());
            int i = tDataMapper.deleteTData(form.getId());
            System.out.println(i2);
            System.out.println(i1);
            System.out.println(i);
            return SysResult.success(Status.SUCCESS);
        }
        return SysResult.error(Status.FAILURE);
    }
}
