package com.swp.dataweb.utils;

import com.alibaba.fastjson.JSONObject;
import com.swp.dataweb.entity.Item;
import com.swp.dataweb.entity.response.ExcelExporter;
import com.swp.dataweb.entity.response.Status;
import com.swp.dataweb.exception.SuRuntimeException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

public class ExcelWriter {

    /**
     * 导出数据
     *
     * @param datasMap
     * @param itemsMap
     * @return
     */
    public static XSSFWorkbook exportData(HashMap<Long, HashMap<Long, String>> datasMap,
                                          HashMap<Long, HashMap<Long, Item>> itemsMap,
                                          ExcelExporter excelExporter) {

        // 生成xlsx的Excel  hssfworkbook只能生成xls文件
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 生成Sheet表，写入第一行的列头

//        Sheet sheet = buildDataSheet(workbook,sheetName);

        //获取单个表单的所有的问项和数据。 然后获取表单名称,然后遍历所有的表单

        for (Long formId : itemsMap.keySet()) {
            HashMap<Long, Item> items = itemsMap.get(formId);
            Sheet sheet = buildDataSheet(workbook, items);
            int rowNum = 1;
            HashMap<Long, String> dataMap = datasMap.get(formId);
            if(dataMap==null||dataMap.isEmpty()) throw new SuRuntimeException(Status.EXPORT_ERROR);
            for (String value : dataMap.values()) {
                JSONObject jsonObj = JSONObject.parseObject(value);
                Row row = sheet.createRow(rowNum++);
                for (Map.Entry<String, Object> json : jsonObj.entrySet()) {
                    for (Item item : items.values()) {
                        if (item.getId().toString().equals(json.getKey())) {
                            //单元格
                            Cell cell = row.createCell(item.getOrderNum()-1);
                            String data = json.getValue().toString();
                            if (null != data) {
                                cell.setCellValue(data);
                            } else {
                                cell.setCellValue("");
                            }
                        }
                    }
                }
            }
        }
        return workbook;
    }


    /**
     * 生成一个sheet表，并写入第一行数据(列头) (传入问项名称list与id)
     *
     * @param workbook 工作簿对象
     * @return 已经写入列头的Sheet
     */
    private static Sheet buildDataSheet(Workbook workbook,
                                        HashMap<Long, Item> items) {
        Sheet sheet = null;
        Cell cell = null;
        int count = 0;
        // 构建头单元格样式
        CellStyle cellStyle = buildHeadCellStyle(workbook);
        for (Map.Entry<Long, Item> one : items.entrySet()) {
            sheet = workbook.createSheet(one.getValue().getFormName());
            break;
        }
        assert sheet != null;
        Row head = sheet.createRow(0);
        for (Item item : items.values()) {
            //设置列头宽度
            sheet.setColumnWidth(count, 4500);
            // 写入第一行各列的数据
            cell = head.createCell(item.getOrderNum()-1);
            //设置第一行的标题
            cell.setCellValue(item.getItemName());
            //设置每一行的样式
            cell.setCellStyle(cellStyle);
            count++;
        }
        // 设置默认行高
        sheet.setDefaultRowHeight((short) 500);
        return sheet;
    }

    /**
     * 导出excel(浏览器下载方式)
     *
     * @param fileName 导出的文件名
     * @param response 响应
     * @param workbook 工作表
     */
    public static void doExport(String fileName,
                                HttpServletResponse response,
                                XSSFWorkbook workbook) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8") + ".xlsx");
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 导出excel(本地文件导出)
     *
     * @param fileName 导出的文件名
     * @param workbook 工作表
     */
    public static void doExport(String fileName,
                                XSSFWorkbook workbook) {
        try {
            FileOutputStream fos = new FileOutputStream(fileName + ".xlsx");
            workbook.write(fos);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    /**
     * 设置第一行列头的样式
     *
     * @param workbook 工作簿对象
     * @return 单元格样式对象
     */
    private static CellStyle buildHeadCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        //对齐方式设置
        style.setAlignment(HorizontalAlignment.CENTER);
        //边框颜色和宽度设置
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex()); // 下边框
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex()); // 左边框
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex()); // 右边框
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex()); // 上边框

        //设置背景颜色
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //粗体字设置
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        return style;
    }

}
