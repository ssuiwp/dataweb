package com.swp.dataweb.controller;

import com.swp.dataweb.entity.UserSubjectType;
import com.swp.dataweb.entity.response.SysResult;
import com.swp.dataweb.service.SubjectTypeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("subjectType")
public class SubjectTypeController {

    @Resource
    private SubjectTypeService subjectTypeService;

    /**
     * 添加课题类型
     * @param userSubjectType
     * @return
     */
    @PostMapping("add")
    public SysResult addSubjectType(@RequestBody UserSubjectType userSubjectType){
        boolean result = subjectTypeService.add(userSubjectType);
        if(result) {
            return SysResult.success();
        }
        return SysResult.error();
    }

    @GetMapping("findAll")
    public SysResult getSubjectType(){
        List<UserSubjectType> list = subjectTypeService.getAllSubjectType();
        if(list!=null&&list.size()!=0) {
            return SysResult.success(list);
        }
        return SysResult.error();
    }

    @PutMapping("update")
    public SysResult updateSubjectType(@RequestBody UserSubjectType userSubjectType){
        boolean result = subjectTypeService.update(userSubjectType);
        if(result) {
            return SysResult.success();
        }
        return SysResult.error();
    }

}
