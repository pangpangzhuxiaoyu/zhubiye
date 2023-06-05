package com.zhu.service;

import com.zhu.domain.Student;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
public interface ExcelService {
    public XSSFWorkbook selectAll();
    public XSSFWorkbook selectByIds(int[] studentIds);
}
