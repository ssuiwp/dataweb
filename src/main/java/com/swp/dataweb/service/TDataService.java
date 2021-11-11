package com.swp.dataweb.service;

import com.swp.dataweb.dao.TDataMapper;
import com.swp.dataweb.entity.Subject;
import com.swp.dataweb.entity.response.SysResult;
import com.swp.dataweb.entity.TData;
import com.swp.dataweb.entity.TDataModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TDataService {

    @Resource
    private TDataMapper tDataMapper;

    public SysResult<List<TData>> createTData(TDataModel tDataModel){

        List<TData> tData = tDataModel.getTData();
        tDataMapper.addTData(tData);
        return SysResult.success(tData);
    }


    public SysResult<List<TData>> obtainTData(Subject subject){

        List<TData> tData = tDataMapper.getTData(subject);
        return SysResult.success(tData);
    }


    public SysResult<List<TData>> updateTData(List<TData> tData){


        tDataMapper.updateTData(tData);
        return SysResult.success(tData);
    }



}
