package com.swp.dataweb.controller;

import com.swp.dataweb.entity.Subject;
import com.swp.dataweb.entity.TData;
import com.swp.dataweb.entity.TDataModel;
import com.swp.dataweb.entity.response.Status;
import com.swp.dataweb.entity.response.SysResult;
import com.swp.dataweb.service.TDataService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import java.util.List;


@RestController
@RequestMapping("tdata")
public class TDataController {

    @Resource
    private TDataService tDataService;

    /**
     * 填写数据
     */
    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public SysResult createSubject(@RequestBody TDataModel tDataModel){
        if(tDataModel == null){
            return SysResult.error(Status.FAILURE);
        }

        return tDataService.createTData(tDataModel);
    }

    /**
     * 查询数据
     */
    @PostMapping(value = "/query", consumes = "application/json", produces = "application/json")
    public SysResult obtainTData(@RequestBody Subject subject){
        if (subject == null){
            return SysResult.error(Status.FAILURE);
        }
        return tDataService.obtainTData(subject);
    }

    /**
     * 更新数据
     */
    @PostMapping(value = "/update", consumes = "application/json", produces = "application/json")
    public SysResult updateTData(@RequestBody List<TData> tData){
        if(tData == null){
            return SysResult.error(Status.FAILURE);
        }
        return tDataService.updateTData(tData);
    }




}
