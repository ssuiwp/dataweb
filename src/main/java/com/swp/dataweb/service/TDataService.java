package com.swp.dataweb.service;

import com.swp.dataweb.dao.TDataMapper;
import com.swp.dataweb.entity.Response.Response;
import com.swp.dataweb.entity.Response.Status;
import com.swp.dataweb.entity.TData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TDataService {

    @Resource
    private TDataMapper tDataMapper;

    public Response<List<TData>> createTData(List<TData> tData){

        tDataMapper.addTData(tData);
        return Response.success(tData);
    }


    public Response<List<TData>> obtainTData(Long subjectId){


        List<TData> tData = tDataMapper.getTData(subjectId);
        return Response.success(tData);
    }


    public Response<List<TData>> updateTData(List<TData> tData){


        tDataMapper.updateTData(tData);
        return Response.success(tData);
    }



}
