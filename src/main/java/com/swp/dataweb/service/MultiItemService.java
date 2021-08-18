package com.swp.dataweb.service;

import com.github.pagehelper.Page;
import com.swp.dataweb.dao.MultiItemMapper;
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
public class MultiItemService {

    @Resource
    private MultiItemMapper multiItemMapper;

    /**
     * 创建问项
     * @param multiItem 问项
     * @return
     */
    public Response<MultiItem> createMultiItem(/*User user,*/MultiItem multiItem) {
/*        Response<Subject> response = checkMultiItem(user,multiItem);
        if (response != null) {
            return response;
        }*/
        multiItemMapper.addMultiItem(multiItem);
        return Response.success(multiItem);
    }

    /**
     * 查询问项
     * @param query 问项查询
     * @return
     */
    @Transactional
    public QueryResponse<List<MultiItem>> obtainMultiItem(MultiItemQuery query){
        List<MultiItem> multiItems = multiItemMapper.getMultiItem(query);
        PageInfo p = new PageInfo();
        p.setTotalCount(multiItemMapper.getTotal());
        return QueryResponse.success(multiItems,p);
    }

    private static Response<MultiItem> checkMultiItem(/*User user,*/ MultiItem multiItem){
        if(isEmpty(multiItem.getName())){
            return Response.error(Status.SUBJECT_NAME_EMPTY);
        }
        return null;
    }
}
