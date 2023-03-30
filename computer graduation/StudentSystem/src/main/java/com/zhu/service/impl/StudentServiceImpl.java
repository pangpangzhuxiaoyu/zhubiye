package com.zhu.service.impl;

import com.zhu.dao.StudentDao;
import com.zhu.domain.Student;
import com.zhu.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;

    public List<Student> selectAll() {

        return studentDao.selectAll();

    }
}
