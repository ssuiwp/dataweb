package com.swp.dataweb.controller;

import com.swp.dataweb.entity.MultiItem;
import com.swp.dataweb.entity.MultiItemQuery;
import com.swp.dataweb.entity.Response.Response;
import com.swp.dataweb.entity.Response.Status;
import com.swp.dataweb.service.MultiItemService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/dataweb/multiItem")
public class MultiItemController {

    @Resource
    private MultiItemService multiItemService;

    /**
     * 添加问项
     */
    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public Response createMultiItem(@RequestBody MultiItem multiItem){
        if(multiItem == null){
            return Response.error(Status.MULTIITEM_EMPTY);
        }
        return multiItemService.createMultiItem(multiItem);
    }

    /**
     * 查询问项
     */
    @PostMapping(value = "/query", consumes = "application/json", produces = "application/json")
    public Response obtainMultiItem(@RequestBody MultiItemQuery query){
        if (query == null) {
            return Response.error(Status.MULTIITEM_QUERY_EMPTY);
        }
        return multiItemService.obtainMultiItem(query);
    }



}
