package com.zhu.controller;

import com.zhu.service.ExcelService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@RestController
@RequestMapping("/excels")
public class ExcelController {
    @Autowired
    ExcelService excelService;

    @GetMapping
    public void export(HttpServletResponse response) {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=AzcItemInfo.xlsx;charset=UTF-8");
        XSSFWorkbook workbook = excelService.selectAll();
        try {
            OutputStream output  = response.getOutputStream();
            BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
            workbook.write(bufferedOutPut);
            bufferedOutPut.flush();
            bufferedOutPut.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //导出部分
    @GetMapping("/Ids/{studentIds}")
    public void exportSelect(@PathVariable int[] studentIds,HttpServletResponse response) {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=AzcItemInfo.xlsx;charset=UTF-8");
        XSSFWorkbook workbook = excelService.selectByIds(studentIds);
        try {
            OutputStream output  = response.getOutputStream();
            BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
            workbook.write(bufferedOutPut);
            bufferedOutPut.flush();
            bufferedOutPut.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
