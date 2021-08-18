package com.swp.dataweb.service;

import com.github.pagehelper.Page;
import com.swp.dataweb.dao.FormMapper;
import com.swp.dataweb.dao.TDataMapper;
import com.swp.dataweb.entity.*;
import com.swp.dataweb.entity.Response.QueryResponse;
import com.swp.dataweb.entity.Response.Response;
import com.swp.dataweb.entity.Response.Status;
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
    public Response<Form> createForm(Form form) {
//        Response<Form> response = checkForm(form);
//        if (response != null) {
//            return response;
//        }
        formMapper.addRelation(form);
        formMapper.addForm(form);
        return Response.success(form);
    }

    /**
     * 更新表单
     */
    @Transactional
    public Response<Form> updateForm(Form form){


        formMapper.updateRelation(form);
        formMapper.updateForm(form);
        return Response.success(form);
    }


    /**
     * 查找表单
     */
    @Transactional
    public QueryResponse<List<Form>> obtainForm(FormQuery query){
        List<Form> forms = formMapper.getForm(query);
        PageInfo p = new PageInfo();
        int total = formMapper.getTotal();
        p.setTotalCount(total);
        return QueryResponse.success(forms,p);
    }

    /**
     * 检测表单
     * @param user 用户
     * @param form 表单
     * @return 如果没有返回null
     */
    public static Response<Form> checkForm(User user, Form form){
        if(isEmpty(form.getName())){
            return Response.error(Status.FORM_NAME_EMPTY);
        }
        if(isEmpty(form.getSubject().getName())){
            return Response.error(Status.FORM_SUBJECT_EMPTY);
        }
        form.setCreator(user.getName());
        return null;
    }

    /**
     * 删除表单
     */
    @Transactional
    public Response deleteForm(Form form){
        if(form != null){
            int i2 = formMapper.deleteForm(form.getId());
            int i1 = formMapper.deleteRelation(form.getId());
            int i = tDataMapper.deleteTData(form.getId());
            System.out.println(i2);
            System.out.println(i1);
            System.out.println(i);
            return Response.success(Status.SUCCESS);
        }
        return Response.error(Status.FAILURE);
    }
}
