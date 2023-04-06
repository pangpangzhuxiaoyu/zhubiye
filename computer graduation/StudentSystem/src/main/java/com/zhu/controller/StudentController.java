package com.zhu.controller;

import com.zhu.domain.PageBean;
import com.zhu.domain.Student;
import com.zhu.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studnetService;

    @GetMapping
    public Result selectAllByPage(@RequestParam("curPage") int curPage,
                                  @RequestParam("pageSize") int pageSize  ) {
        //获得
        PageBean<Student> pageBean = studnetService.selectAllByPage(curPage,pageSize);
        Integer code =pageBean.getRowsStudents() !=null?Code.GET_OK:Code.GET_ERROR;
        String msg=pageBean.getRowsStudents() !=null?"":"数据查询失败，请重试哦！";
        return new Result(code,msg,pageBean);
    }
    @DeleteMapping("/{studentId}")
    public Result deleteById(@PathVariable String studentId){
        boolean flag = studnetService.deleteById(studentId);
        return new Result(flag?Code.DELETE_OK:Code.DELETE_ERROR,flag);
    }
}
