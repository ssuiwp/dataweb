package com.swp.dataweb.controller;

import com.swp.dataweb.entity.*;
import com.swp.dataweb.entity.query.FormQuery;
import com.swp.dataweb.entity.response.Status;
import com.swp.dataweb.entity.response.SysResult;
import com.swp.dataweb.service.FormService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import java.util.List;


@RestController
@RequestMapping("form")
public class FormController {

    @Resource
    private FormService formService;

    /**
     * 增加表单
     * @param form 表单
     * @return 创建表单成功
     */
    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public SysResult<Form> createForm(@RequestBody Form form){
        if(form == null){
            return SysResult.error(Status.FORM_EMPTY);
        }
        return formService.createForm(form);
    }

    /**
     * 查询表单
     */
    @PostMapping(value = "/query", consumes = "application/json", produces = "application/json")
    public SysResult<List<Form>> obtainForm(@RequestBody FormQuery query){
        if(query == null){
            return SysResult.error(Status.FORM_QUERY_EMPTY);
        }
        return formService.obtainForm(query);
    }

    /**
     * 更新表单
     */
    @PostMapping(value = "/update", consumes = "application/json", produces = "application/json")
    public SysResult<Form> updateForm(@RequestBody  Form form){
        if(form == null){
            return SysResult.error(Status.FORM_EMPTY);
        }

        return formService.updateForm(form);
    }

    /**
     * 删除表单
     */
    @PostMapping(value = "/delete", consumes = "application/json", produces = "application/json")
    public SysResult deleteForm(@RequestBody Form form){
        if(form == null){
            return SysResult.error(Status.FORM_EMPTY);
        }
        return formService.deleteForm(form);
    }
}
