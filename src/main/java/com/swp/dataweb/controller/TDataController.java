package com.swp.dataweb.controller;

import com.swp.dataweb.entity.Response.Response;
import com.swp.dataweb.entity.Subject;
import com.swp.dataweb.entity.SubjectQuery;
import com.swp.dataweb.entity.TData;
import com.swp.dataweb.service.TDataService;
import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import java.util.List;

import static com.swp.dataweb.entity.Response.Status.*;

@RestController
@RequestMapping("/dataweb/tdata")
public class TDataController {

    @Resource
    private TDataService tDataService;

    /**
     * 填写数据
     */
    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public Response createSubject(@RequestBody List<TData> tData){
        if(tData == null){
            return Response.error(FAILURE);
        }

        return tDataService.createTData(tData);
    }

    /**
     * 查询数据
     */
    @PostMapping(value = "/query", consumes = "application/json", produces = "application/json")
    public Response obtainTData(@RequestBody Subject subject){
        if (subject == null){
            return Response.error(FAILURE);
        }
        return tDataService.obtainTData(subject.getId());
    }

    /**
     * 更新数据
     */
    @PostMapping(value = "/update", consumes = "application/json", produces = "application/json")
    public Response updateTData(@RequestBody List<TData> tData){
        if(tData == null){
            return Response.error(FAILURE);
        }
        return tDataService.updateTData(tData);
    }




}
