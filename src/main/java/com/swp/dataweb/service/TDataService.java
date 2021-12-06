package com.swp.dataweb.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.swp.dataweb.dao.FormMapper;
import com.swp.dataweb.dao.ItemMapper;
import com.swp.dataweb.dao.SubjectMapper;
import com.swp.dataweb.dao.TDataMapper;
import com.swp.dataweb.entity.*;
import com.swp.dataweb.entity.response.ExcelExporter;
import com.swp.dataweb.entity.response.SysResult;
import com.swp.dataweb.utils.ExcelWriter;
import com.swp.dataweb.utils.Utils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class TDataService {

    @Resource
    private TDataMapper tDataMapper;
    @Resource
    private SubjectMapper subjectMapper;
    @Resource
    private FormMapper formMapper;
    @Resource
    private ItemMapper itemMapper;

    public SysResult createTData(TDataModel tDataModel) {
        long formId = tDataModel.getSubjectForm()[1];
        String s = JSON.toJSON(tDataModel.getTData()).toString();
        TData data = new TData()
                .setData(s)
                .setCreator(Utils.getNickName())
                .setFormId(formId);
        tDataMapper.addTData(data, Utils.getNickName());
        return SysResult.success();
    }


    public SysResult obtainTData(long formId) {
        List<TData> list = tDataMapper.selectList(
                new QueryWrapper<TData>().eq("form_id", formId));
        return SysResult.success(list);
    }


    public SysResult updateTData(TDataModel tDataModel) {
        long dataId = tDataModel.getSubjectForm()[2];
        String s = JSON.toJSON(tDataModel.getTData()).toString();
        TData data = new TData()
                .setData(s)
                .setCreator(Utils.getNickName())
                .setId(dataId);
        tDataMapper.updateById(data);
        return SysResult.success();
    }


    public SysResult getSubjectAndFormName() {
        List<Subject> subjects = subjectMapper.selectList(
                new QueryWrapper<Subject>().eq("user_id", Utils.getUserId())
        );
        List<Subject> selectPartnerSubject = subjectMapper.selectPartnerSubject(Utils.getUserId());
        subjects.addAll(selectPartnerSubject);

        for (Subject subject : subjects) {
            subject.setChildren(formMapper.selectList(
                    new QueryWrapper<Form>().eq("subject_id", subject.getId())
            ));
        }

        return SysResult.success(subjects);
    }

    /**
     * 筛选用户选择的数据并返回excel对象让其上传文件
     */
    public void filterData(ExcelExporter excelExporter, HttpServletResponse resp) {
        Long subjectId = excelExporter.getSubjectId();
        List<Long> formIds = excelExporter.getFormIds();
        String subjectName = excelExporter.getSubjectName();
        //datasMap为formid:   dataMap(dataId:jsonData)
        //itemsMap为formid:   ItemMap(ItemId:Item)
        HashMap<Long, HashMap<Long, String>> datasMap = new HashMap<>();
        HashMap<Long, HashMap<Long, Item>> itemsMap = new HashMap<>();
        List<TData> datas = new ArrayList<>();
        List<Item> items = new ArrayList<>();
        if (formIds == null || formIds.isEmpty()) {
            //查询所有的表单数据
            datas = tDataMapper.getTDataBySubjectId(subjectId,
                    excelExporter.getStartDate(),excelExporter.getEndDate());
            //查询表单关联的问项
            items = itemMapper.getItemBySubjectId(subjectId);
        } else {
            //查询表单数据   在针对过滤条件进行筛选
            datas = tDataMapper.getTDataByFormIds(formIds,
                    excelExporter.getStartDate(),excelExporter.getEndDate());
            //查询表单关联的问项
            items = itemMapper.getItemByFormIds(formIds);
        }
        //分类处理表单数据和问项标题
        handleDataMap(datasMap, datas);
        handleItemMap(itemsMap, items);

        XSSFWorkbook workbook = ExcelWriter.exportData(datasMap, itemsMap, excelExporter);
        ExcelWriter.doExport(subjectName, resp, workbook);
    }

    /**
     * 处理表单数据
     *
     * @param map
     * @param datas
     * @return
     */
    private void handleDataMap(HashMap<Long, HashMap<Long, String>> map, List<TData> datas) {
        for (TData data : datas) {
            Long formId = data.getFormId();
            if(map.containsKey(formId)){
                HashMap<Long, String> dataMap = map.get(formId);
                dataMap.put(data.getId(),data.getData());
            }else{
                HashMap<Long, String> dataMap = new HashMap<>();
                dataMap.put(data.getId(),data.getData());
                map.put(formId,dataMap);
            }
        }
    }

    /**
     * 处理问项
     *
     * @param map
     * @param items
     * @return
     */
    private void handleItemMap(HashMap<Long, HashMap<Long, Item>> map, List<Item> items) {
        for (Item item : items) {
            Long formId = item.getFormId();
            if(map.containsKey(formId)){
                HashMap<Long, Item> itemMap = map.get(formId);
                itemMap.put(item.getId(),item);
            }else{
                HashMap<Long, Item> itemMap = new HashMap<>();
                itemMap.put(item.getId(),item);
                map.put(formId,itemMap);
            }
        }
    }

    /**
     * 使用过滤条件筛选数据
     */
    private void doFilterData(HashMap<Long, HashMap<Long, String>> datasMap,
                              HashMap<Long, HashMap<Long, Item>> itemsMap){

    }

}
