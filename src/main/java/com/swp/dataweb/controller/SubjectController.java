package com.swp.dataweb.controller;

import com.swp.dataweb.entity.Subject;
import com.swp.dataweb.entity.query.SubjectQuery;
import com.swp.dataweb.entity.UserSubjectType;
import com.swp.dataweb.entity.request.SubjectPartner;
import com.swp.dataweb.entity.response.PageResult;
import com.swp.dataweb.entity.response.SysResult;
import com.swp.dataweb.service.SubjectService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("subject")
public class SubjectController {

    @Resource
    private SubjectService subjectService;

    /**
     * 添加课题
     *
     * @param subject 课题
     * @return 课题类
     */
    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public SysResult createSubject(@Validated @RequestBody Subject subject) {
        boolean result = subjectService.createSubject(subject);
        if (result) {
            return SysResult.success();
        }
        return SysResult.error();
    }

    /**
     * 查询课题
     *
     * @param query 课题查询类
     * @return 查询类
     */
    @PostMapping(value = "/query")
    public SysResult obtainSubject(@RequestBody SubjectQuery query) {
        return subjectService.obtainSubject(query);
    }

    @GetMapping("getSubjectIN")
    public SysResult findIdAndName() {
        return subjectService.findIdAndName();
    }

    /**
     * 更新课程
     *
     * @return 更新后的课题
     */
    @PostMapping(value = "/update")
    public SysResult updateSubject(@Validated @RequestBody Subject subject) {
        return subjectService.updateSubject(subject);
    }

    /**
     * 删除课题
     *
     * @return 删除结果
     */
    @DeleteMapping(value = "/delete/{id}")
    public SysResult deleteSubject(@PathVariable long id) {
        return subjectService.deleteSubject(id);
    }

    @GetMapping(value = "/test", produces = "application/json")
    public String test() {

        return "hello world!";
    }

    /** 添加课题类型如果存在就更新 */
    @PostMapping("/addType")
    public SysResult addType(@Validated @RequestBody UserSubjectType type){
        boolean result = subjectService.addType(type);
        if(result) {
            return SysResult.success();
        }
        return SysResult.error();
    }

    @PostMapping("/findAllType")
    public SysResult findAllType(@RequestBody PageResult type){
        type =  subjectService.findAllType(type);
        return SysResult.success(type);
    }

    /** 分页查询课题类型 */
    @PostMapping("/type")
    public SysResult findType(@RequestBody PageResult type){
        type =  subjectService.findType(type);
        return SysResult.success(type);
    }
    /** 删除课题类型 */
    @DeleteMapping("/deleteType/{id}")
    public SysResult deleteType(@PathVariable long id){
        boolean result = subjectService.deleteType(id);
        if(result) {
            return SysResult.success();
        }
        return SysResult.error();
    }


    /**
     * 添加合伙人
     */
    @PostMapping("/addPartner")
    public SysResult addPartner(@RequestBody SubjectPartner partner){
        boolean result = subjectService.addPartner(partner);
        if(result) {
            return SysResult.success();
        }
        return SysResult.error();
    }
    /**
     * 查询合伙人
     */
    @GetMapping("/findPartner/{subjectId}")
    public SysResult findPartner(@PathVariable Long subjectId){
        return subjectService.findPartner(subjectId);

    }
}
