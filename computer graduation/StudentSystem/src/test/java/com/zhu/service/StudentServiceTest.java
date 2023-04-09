package com.zhu.service;

import com.alibaba.fastjson.JSONArray;
import com.zhu.config.SpringConfig;
import com.zhu.domain.Course;
import com.zhu.domain.PageBean;
import com.zhu.domain.Student;
import com.zhu.domain.StudentWithScore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.xml.crypto.Data;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class StudentServiceTest {
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService  courseService;
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
       // s.setStudentId("20230022");
        studentService.deleteById(s.studentId);
    }
    @Test
    public void deleteByIds(){
        int[] studentIds=new int[]{20230004};
        studentService.deleteByIds(studentIds);
    }

    @Test
    public void selectcou(){
      List<Course> s = courseService.selectCourseName();
       System.out.println(s.toString());
    }
    @Test
    public void add(){
        Student s=new Student(1,"aa",Student.Gender.男, new Date("2022/01/02"),"515","asdsa");
        //String scores=jsonObject.getString("scores");

        //String scores="[{courseId: 1001, courseName: "语文", score: "1"},{courseId: 1002, courseName: "数学", score: "1"},{courseId: 1003, courseName: "英语", score: "1"}]";
        String scores = "[{courseId: 1001, courseName: \"语文\", score: \"1\"},{courseId: 1002, courseName: \"数学\", score: \"1\"},{courseId: 1003, courseName: \"英语\", score: \"1\"}]";

        List<StudentWithScore> studentWithScoreList = JSONArray.parseArray(scores,StudentWithScore.class);
        for(StudentWithScore studentWithScore:studentWithScoreList){
            studentWithScore.setStudentId(s.studentId);
        }
       // List<StudentWithScore> ss=
        studentService.studentAdd(s,studentWithScoreList);
    }

}
