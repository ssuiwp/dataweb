package com.swp.dataweb.controller;

import com.swp.dataweb.entity.MultiItem;
import com.swp.dataweb.entity.MultiItemQuery;
import com.swp.dataweb.entity.response.Status;
import com.swp.dataweb.entity.response.SysResult;
import com.swp.dataweb.service.MultiItemService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("item")
public class MultiItemController {

    @Resource
    private MultiItemService multiItemService;

    /**
     * 添加问项
     */
    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public SysResult createMultiItem(@RequestBody MultiItem multiItem){
        if(multiItem == null){
            return SysResult.error();
        }
        return multiItemService.createMultiItem(multiItem);
    }

    /**
     * 查询问项
     */
    @PostMapping(value = "/query", consumes = "application/json", produces = "application/json")
    public SysResult obtainMultiItem(@RequestBody MultiItemQuery query){
        if (query == null) {
            return SysResult.error();
        }
        return multiItemService.obtainMultiItem(query);
    }



}
