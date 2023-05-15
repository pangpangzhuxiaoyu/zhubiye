package com.zhu.controller;

import com.zhu.service.ExcelService;
import org.apache.log4j.Logger;
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
    private static Logger logger=Logger.getLogger(ExcelController.class);
    @Autowired
    ExcelService excelService;

   /* @GetMapping
    public void export(HttpServletResponse response) {
        logger.info("导出全部学生按钮，export方法被调用");
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=AzcItemInfo.xlsx;charset=UTF-8");
        XSSFWorkbook workbook = excelService.selectAll();
        try {
            OutputStream output  = response.getOutputStream();
            BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
            workbook.write(bufferedOutPut);
            bufferedOutPut.flush();
            bufferedOutPut.close();
            logger.info("export方法被调用结束");
        } catch (IOException e) {
            logger.error("出现异常，到处失败");
            e.printStackTrace();
        }
    }*/

    //导出部分
    @GetMapping("/Ids/{studentIds}")
    public void export(@PathVariable int[] studentIds,HttpServletResponse response) {
        logger.info("导出选中学生按钮，export方法被调用");
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=AzcItemInfo.xlsx;charset=UTF-8");
        XSSFWorkbook workbook;
        if(studentIds==null){
            workbook = excelService.selectAll();
        }else{
            workbook = excelService.selectByIds(studentIds);
        }
        try {
            OutputStream output  = response.getOutputStream();
            BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
            workbook.write(bufferedOutPut);
            bufferedOutPut.flush();
            bufferedOutPut.close();
            logger.info("export方法被调用结束");
        } catch (IOException e) {
            logger.error("出现异常，导出失败");
            e.printStackTrace();
        }
    }




}
