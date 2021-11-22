package com.swp.dataweb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.swp.dataweb.dao.ItemMapper;
import com.swp.dataweb.entity.Item;
import com.swp.dataweb.entity.query.ItemQuery;
import com.swp.dataweb.entity.response.PageResult;
import com.swp.dataweb.entity.response.Status;
import com.swp.dataweb.entity.response.SysResult;
import com.swp.dataweb.utils.Utils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


import java.util.List;

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
        item.setCreator(Utils.getNickName());
        item.setUserId(Utils.getUserId());
        int row1 = itemMapper.insert(item);
        return row1 == 1;
    }

    /**
     * 查询问项
     *
     * @param query 问项查询
     * @return
     */
    @Transactional
    public SysResult obtainItem(ItemQuery query) {
        PageResult page = new PageResult();
        page.setTotal((long) itemMapper.getTotal(Utils.getUserId()));
        long start = query.getSize() * (query.getCurrent() - 1);
        List<Item> items = itemMapper.getItems(query, start,Utils.getUserId());
        page.setRaws(items);

        return SysResult.success(Status.SUCCESS, page);
    }

    public boolean deleteItem(Long itemId) {
        int row = itemMapper.deleteById(itemId);
        return row == 1;
    }

    public SysResult updateItem(Item item) {
        int i = itemMapper.updateById(item);
        if (i == 1) {
            return SysResult.success(item);
        }
        return SysResult.error();
    }

    public SysResult findAll() {
        List<Item> list = itemMapper.selectList(
                new QueryWrapper<Item>().eq("user_id",Utils.getUserId()
        ));
        return SysResult.success(list);
    }

    public SysResult getItem(long formId) {
        List<Item> list = itemMapper.getItem(formId);
        return SysResult.success(list);
    }
}
