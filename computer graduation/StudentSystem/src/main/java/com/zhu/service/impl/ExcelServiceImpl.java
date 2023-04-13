package com.zhu.service.impl;

import com.zhu.dao.StudentDao;
import com.zhu.domain.Student;
import com.zhu.pojo.ExcelBean;
import com.zhu.pojo.ExcelUtil;
import com.zhu.service.ExcelService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExcelServiceImpl implements ExcelService {
    @Autowired
    StudentDao studentDao;
    @Override
    public XSSFWorkbook selectAll() {
        XSSFWorkbook xssfWorkbook = null;
        try {
            List<Student> studentList = studentDao.selectAll();
            List<ExcelBean> excel = new ArrayList<>();
            Map<Integer, List<ExcelBean>> map = new LinkedHashMap<>();

            //设置标题栏
            excel.add(new ExcelBean("学生ID", "studentId", 0));
            excel.add(new ExcelBean("学生姓名", "studentName", 0));
            excel.add(new ExcelBean("性别", "studentGender", 0));
            excel.add(new ExcelBean("出生日期", "studentBirth", 0));
            excel.add(new ExcelBean("电话号码", "studentTel", 0));
            excel.add(new ExcelBean("各科成绩", "Scores.subjectScore", 0));
            map.put(0, excel);
            String sheetName = "Student";
            xssfWorkbook = ExcelUtil.createExcelFile(Student.class, studentList, map, sheetName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xssfWorkbook;

    }
}
