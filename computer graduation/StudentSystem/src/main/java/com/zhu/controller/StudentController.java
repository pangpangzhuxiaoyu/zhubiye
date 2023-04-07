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

    /**
     * 前端需要的科目信息
     * @return
     */
    @GetMapping("/subject")
    public Result selectSubjectName(){
       List<Student> student = studentService.selectSubjectName();
       Integer code = student != null?Code.GET_OK:Code.GET_ERROR;
       String msg = student != null?"":"科目查询出错了";
       return new Result(code,msg,student);
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
}
