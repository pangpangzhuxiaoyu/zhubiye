package com.zhu.controller;

import com.zhu.domain.Student;
import com.zhu.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studnetService;

    @GetMapping("/{}")
    public List<Student> selectAll() {
        return studnetService.selectAll();

    }
}
