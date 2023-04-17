package com.zhu.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.zhu.config.SpringConfig;
import com.zhu.domain.*;
import com.zhu.pojo.PageBean;
import com.zhu.pojo.PojoByCondition;
import com.zhu.pojo.StudentWithScore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
       //PageBean<Student> student = studentService.selectAllByPage(1,30);
    }
    @Test
    public void testSelectByPageWithCondition(){
        PojoByCondition pb=new PojoByCondition();
        pb.studentId=null;
        pb.subjectName="";
        pb.studentName="";
        pb.maxScore=100;
        pb.minScore=0;
        PageBean<Student> student = studentService.selectAllByPageWithCondition(1,15,pb);
        System.out.println(student);
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

    @Test
    public void update(){
        String s="{\"student\":{\"studentId\":20230017,\"studentName\":\"朱赫\",\"studentGender\":\"男\",\"studentBirth\":\"2001-01-31T16:00:00.000Z\",\"studentTel\":\"13596929473\",\"studentAdress\":\"辽宁省大连市中山区\",\"scores\":[{\"studentId\":20230017,\"courseId\":1001,\"subjectScore\":\"100\",\"course\":{\"courseId\":1001,\"courseName\":\"语文\"}},{\"studentId\":20230017,\"courseId\":1002,\"subjectScore\":\"100\",\"course\":{\"courseId\":1002,\"courseName\":\"数学\"}},{\"studentId\":20230017,\"courseId\":1003,\"subjectScore\":\"100\",\"course\":{\"courseId\":1003,\"courseName\":\"英语\"}}]}}";
        com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSON.parseObject(s);
        String studentJson = jsonObject.getString("student");
        Student student = JSON.parseObject(studentJson, Student.class);
        boolean flag = studentService.studentUpdate(student);
    }



}
