package com.zhu.controller;

import com.zhu.domain.Course;
import com.zhu.service.CourseService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/courseNames")
public class CourseController {
    private static Logger logger=Logger.getLogger(CourseController.class);
    @Autowired
    private CourseService courseService;
    /**
     * 前端需要的科目信息
     * @return
     */
    @GetMapping
    public Result selectSubjectName(){
        try{
            logger.info("查询所有科目名称，selectSubjectName方法被调用");
            List<Course> courseName = courseService.selectCourseName();
            Integer code = courseName != null?Code.GET_OK:Code.GET_ERROR;
            String msg = courseName != null?"":"科目查询出错了";
            logger.info("selectSubjectName方法被调用结束");
            return new Result(code,msg,courseName);
        }catch(Exception e){
            logger.error("出现异常，查询所有科目名称失败");
            e.printStackTrace();
            return new Result(Code.GET_ERROR);
        }
    }
}
