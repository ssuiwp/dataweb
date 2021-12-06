package com.swp.dataweb.controller;

import com.swp.dataweb.entity.Form;
import com.swp.dataweb.entity.query.FormQuery;
import com.swp.dataweb.entity.request.ItemSort;
import com.swp.dataweb.entity.response.Status;
import com.swp.dataweb.entity.response.SysResult;
import com.swp.dataweb.service.FormService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("form")
@Slf4j
public class FormController {

    @Resource
    private FormService formService;

    /**
     * 增加表单
     *
     * @param form 表单
     * @return 创建表单成功
     */
    @PostMapping(value = "/add")
    public SysResult createForm(@Validated @RequestBody Form form) {
        try {
            return formService.createForm(form);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return SysResult.error(Status.FAILURE, "表单添加失败", null);
    }

    /**
     * 分页查询表单
     */
    @PostMapping(value = "/query")
    public SysResult obtainForm(@RequestBody FormQuery query) {

        return formService.obtainForm(query);
    }

    @GetMapping("findAll/{subjectId}")
    public SysResult findAll(@PathVariable Long subjectId) {
        return formService.findAll(subjectId);
    }

    /**
     * 更新表单
     */
    @PostMapping(value = "/update")
    public SysResult updateForm(@RequestBody Form form) {
        try {
            return formService.updateForm(form);
        } catch (Exception e) {
            log.error("表单更新失败");
        }
        return SysResult.error();
    }

    /**
     * 修改表单问项顺序
     */
    @PostMapping(value = "/updateFormItemSort")
    public SysResult updateFormItemSort(@RequestBody ItemSort itemSort) {
        return formService.updateFormItemSort(itemSort);
    }

    /**
     * 添加表单问项
     */
    @PostMapping(value = "/addFormItem")
    public SysResult addFormItem(@RequestBody Form form) {
        return formService.addFormItem(form);
    }

    /**
     * 删除表单问项
     */
    @PostMapping(value = "/updateFormItem")
    public SysResult deleteFormItem(@RequestBody Form form) {
        return formService.deleteFormItem(form);
    }

    /**
     * 删除表单
     */
    @PostMapping(value = "/delete")
    public SysResult deleteForm(@RequestBody Form form) {
        if (form == null) {
            return SysResult.error(Status.FORM_EMPTY);
        }
        return formService.deleteForm(form);
    }

    /**
     * 获取表单关联的所有问项id
     */
    @GetMapping(value = "/getItemIds/{formId}")
    public SysResult getItemIds(@PathVariable Long formId) {
        if (formId == null) {
            return SysResult.error(Status.ITEM_FORM_EMPTY);
        }
        return formService.getItemIds(formId);
    }
}
