package com.zhu.service;

import com.zhu.config.SpringConfig;
import com.zhu.domain.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class StudentServiceTest {
    @Autowired
    private StudentService studentService;
    @Test
    public void testSelectAll(){
        List<Student> studnet =studentService.selectAll();
        System.out.println(studnet);

    }
}
