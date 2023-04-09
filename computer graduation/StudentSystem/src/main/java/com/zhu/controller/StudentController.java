package com.zhu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.zhu.domain.PageBean;
import com.zhu.domain.Student;
import com.zhu.domain.StudentWithScore;
import com.zhu.service.StudentService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public Result selectAllByPage(@RequestParam("curPage") int curPage,
                                  @RequestParam("pageSize") int pageSize  ) {
        //获得
        PageBean<Student> pageBean = studentService.selectAllByPage(curPage,pageSize);
        Integer code =pageBean.getRowsStudents() !=null?Code.GET_OK:Code.GET_ERROR;
        String msg=pageBean.getRowsStudents() !=null?"":"数据查询失败，请重试哦！";
        return new Result(code,msg,pageBean);
    }


    @DeleteMapping("/{studentId}")
    public Result deleteById(@PathVariable Integer studentId){
        boolean flag = studentService.deleteById(studentId);
        //返回删除结果
        return new Result(flag?Code.DELETE_OK:Code.DELETE_ERROR,flag);
    }

    //为了区别于单条删除，在请求URL上需要添加Ids
    @DeleteMapping("/Ids/{studentIds}")
    public Result deleteByIds(@PathVariable int[] studentIds){
       boolean flag = studentService.deleteByIds(studentIds);
        //返回删除结果
       return new Result(flag?Code.DELETE_OK:Code.DELETE_ERROR,flag);
    }
    @PostMapping
    public Result studentAdd(@RequestBody String requestBody){
         //获得前端传来的学生信息
        com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSON.parseObject(requestBody);
        String studentJson = jsonObject.getString("student");
        Student student = JSON.parseObject(studentJson, Student.class);
        //获得前端传来的学生成绩信息*/
        String scores=jsonObject.getString("scores");
        List<StudentWithScore> studentWithScoreList = JSONArray.parseArray(scores,StudentWithScore.class);
        boolean flag = studentService.studentAdd(student,studentWithScoreList);
        return new Result(flag?Code.SAVE_OK.intValue():Code.SAVE_ERROR,flag);
    }
}

