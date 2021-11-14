package com.swp.dataweb.service;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.swp.dataweb.dao.ItemMapper;
import com.swp.dataweb.entity.Item;
import com.swp.dataweb.entity.MultiItemQuery;
import com.swp.dataweb.entity.UserSubjectType;
import com.swp.dataweb.entity.response.Status;
import com.swp.dataweb.entity.response.SysResult;
import com.swp.dataweb.utils.Utils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


import java.util.List;

import static org.apache.commons.lang3.StringUtils.isEmpty;

@Service
public class ItemService {

    @Resource
    private ItemMapper itemMapper;

    /**
     * 创建问项
     *
     * @param item 问项
     * @return
     */
    @Transactional
    public boolean addItem(Item item) {
        item.setCreator(Utils.getUserName());
        int row1 = itemMapper.insert(item);
//        Item one = itemMapper.selectOne(new QueryWrapper<>(item));
        int row2 = itemMapper.addItem(Utils.getUserId(), item.getId());
        return row1 == 1 && row2 == 1;
    }

    /**
     * 查询问项
     *
     * @param query 问项查询
     * @return
     */
    @Transactional
    public SysResult obtainMultiItem(MultiItemQuery query) {
        List<Item> multiItems = itemMapper.getMultiItem(query);
//        PageInfo p = new PageInfo();
//        p.setTotal(multiItemMapper.getTotal());
        return SysResult.success(Status.SUCCESS, null);
    }

    private static SysResult checkMultiItem(/*User user,*/ Item item) {
        if (isEmpty(item.getItemName())) {
            return SysResult.error(Status.SUBJECT_NAME_EMPTY);
        }
        return null;
    }

    public List<Item> findItem(String itemName) {

        List<Item> item = itemMapper.findItem(Utils.getUserId(),itemName);
        return item;
    }
}
