package com.swp.dataweb.controller;

import com.swp.dataweb.entity.Item;
import com.swp.dataweb.entity.query.ItemQuery;
import com.swp.dataweb.entity.response.SysResult;
import com.swp.dataweb.service.ItemService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("item")
public class ItemController {

    @Resource
    private ItemService itemService;

    /**
     * 添加问项
     */
    @PostMapping(value = "/add")
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
    @PostMapping(value = "/query")
    public SysResult obtainItem(@RequestBody ItemQuery query){

        return itemService.obtainItem(query);
    }

    @GetMapping("findAll")
    public SysResult findAll(){
        return itemService.findAll();
    }

    @GetMapping("getItem/{formId}")
    public SysResult findAll(@PathVariable long formId){
        return itemService.getItem(formId);
    }

    /**
     * 更新问项
     */
    @PostMapping(value = "/update")
    public SysResult updateItem(@Validated @RequestBody Item item){

        return itemService.updateItem(item);
    }

    @DeleteMapping("delete/{itemId}")
    public SysResult getItem(@PathVariable Long itemId){
        boolean result = itemService.deleteItem(itemId);
        if(result) {
            return SysResult.success();
        }
        return SysResult.error();
    }

}
