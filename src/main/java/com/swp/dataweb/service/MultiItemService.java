package com.swp.dataweb.service;

import com.swp.dataweb.dao.MultiItemMapper;
import com.swp.dataweb.entity.MultiItem;
import com.swp.dataweb.entity.MultiItemQuery;
import com.swp.dataweb.entity.response.Status;
import com.swp.dataweb.entity.response.SysResult;
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
    public SysResult<MultiItem> createMultiItem(/*User user,*/MultiItem multiItem) {
/*        SysResult<Subject> SysResult = checkMultiItem(user,multiItem);
        if (SysResult != null) {
            return SysResult;
        }*/
        multiItemMapper.addMultiItem(multiItem);
        return SysResult.success(multiItem);
    }

    /**
     * 查询问项
     * @param query 问项查询
     * @return
     */
    @Transactional
    public SysResult obtainMultiItem(MultiItemQuery query){
        List<MultiItem> multiItems = multiItemMapper.getMultiItem(query);
//        PageInfo p = new PageInfo();
//        p.setTotal(multiItemMapper.getTotal());
        return SysResult.success(Status.SUCCESS,null);
    }

    private static SysResult<MultiItem> checkMultiItem(/*User user,*/ MultiItem multiItem){
        if(isEmpty(multiItem.getName())){
            return SysResult.error(Status.SUBJECT_NAME_EMPTY);
        }
        return null;
    }
}
