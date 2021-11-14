package com.swp.dataweb.controller;

import com.swp.dataweb.entity.Item;
import com.swp.dataweb.entity.MultiItemQuery;
import com.swp.dataweb.entity.UserSubjectType;
import com.swp.dataweb.entity.response.SysResult;
import com.swp.dataweb.service.ItemService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("item")
public class ItemController {

    @Resource
    private ItemService itemService;

    /**
     * 添加问项
     */
    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public SysResult addItem(@Validated @RequestBody Item item){
        boolean result = itemService.addItem(item);
        if(result) {
            return SysResult.success();
        }
        return SysResult.error();
    }

    /**
     * 查询问项
     */
    @PostMapping(value = "/query", consumes = "application/json", produces = "application/json")
    public SysResult obtainMultiItem(@RequestBody MultiItemQuery query){
        if (query == null) {
            return SysResult.error();
        }
        return itemService.obtainMultiItem(query);
    }

    @GetMapping("find/{itemName}")
    public SysResult getItem(@PathVariable String itemName){
        List<Item> list = itemService.findItem(itemName);
        if(list!=null&&list.size()!=0) {
            return SysResult.success(list);
        }
        return SysResult.error();
    }

}
