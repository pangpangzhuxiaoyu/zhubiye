package com.zhu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.zhu.pojo.PageBean;
import com.zhu.pojo.PojoByCondition;
import com.zhu.domain.Student;
import com.zhu.pojo.StudentWithScore;
import com.zhu.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/{condition}") //为了区别于其他的post请求 故要在url上加
    public Result selectAllByPage(@RequestParam("curPage") int curPage,
                                  @RequestParam("pageSize") int pageSize,
                                  @RequestBody String pojo) {
        //获得
        //把前端传过来的检索条件转换为实体类
        com.alibaba.fastjson.JSONObject pojo1= com.alibaba.fastjson.JSON.parseObject(pojo);
        String pojoByConditionJson = pojo1.getString("pojoByCondition");
        PojoByCondition pojoByCondition = null;
        try {
            //将前端传过来的json字符串转换为PojoByCondition对象
            pojoByCondition=JSON.parseObject(pojoByConditionJson, PojoByCondition.class);
        } catch (JSONException e) {
            //捕获异常后返回友好的提示信息给前端
            return new Result(Code.GET_ERROR, "请求参数错误，请检查后重试！", null);
        }

        PageBean<Student> pageBean = studentService.selectAllByPageWithCondition(curPage,pageSize,pojoByCondition);
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
    @PutMapping
    public Result studentUpdate(@RequestBody String requestBody){
        //获得前端传来的学生信息
        com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSON.parseObject(requestBody);
        String studentJson = jsonObject.getString("student");
        Student student = JSON.parseObject(studentJson, Student.class);
        boolean flag = studentService.studentUpdate(student);
        return new Result(flag?Code.UPDATE_OK.intValue():Code.UPDATE_ERROR,flag);
    }

}

