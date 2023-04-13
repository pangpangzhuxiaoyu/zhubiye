package com.zhu.service;

import com.zhu.domain.Student;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

public interface ExcelService {
    public XSSFWorkbook selectAll();
}
