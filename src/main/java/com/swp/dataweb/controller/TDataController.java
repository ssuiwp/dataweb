package com.swp.dataweb.controller;

import com.swp.dataweb.entity.Subject;
import com.swp.dataweb.entity.TData;
import com.swp.dataweb.entity.TDataModel;
import com.swp.dataweb.entity.response.Status;
import com.swp.dataweb.entity.response.SysResult;
import com.swp.dataweb.service.SubjectService;
import com.swp.dataweb.service.TDataService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.List;


@RestController
@RequestMapping("data")
public class TDataController {

    @Resource
    private TDataService tDataService;

    @GetMapping("getSubjectAndFormName")
    public SysResult getSubjectAndFormName(){
        return tDataService.getSubjectAndFormName();
    }

    /**
     * 填写数据
     */
    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public SysResult createSubject(@RequestBody TDataModel tDataModel){

        return tDataService.createTData(tDataModel);

    }

    /**
     * 查询数据
     */
    @GetMapping("query/{formId}")
    public SysResult findData(@PathVariable long formId){
        return tDataService.obtainTData(formId);
    }

    /**
     * 更新数据
     */
    @PostMapping(value = "/update", consumes = "application/json", produces = "application/json")
    public SysResult updateTData(@RequestBody TDataModel tDataModel){
        return tDataService.updateTData(tDataModel);
    }




}
