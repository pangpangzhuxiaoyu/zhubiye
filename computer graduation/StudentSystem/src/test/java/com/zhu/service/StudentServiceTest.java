package com.zhu.service;

import com.zhu.config.SpringConfig;
import com.zhu.domain.PageBean;
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
        /*List<Student> studnet =studentService.selectAll();
        System.out.println(studnet);*/

    }
    @Test
    public void testSelectByPage(){
       PageBean<Student> student = studentService.selectAllByPage(1,30);
    }
    @Test
    public void deleteById(){
        Student s=new Student();
        s.setStudentId("20230014");
        studentService.deleteById(s);
    }
}
