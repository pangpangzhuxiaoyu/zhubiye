package com.zhu.service.impl;

import com.zhu.dao.CourseDao;
import com.zhu.domain.Course;
import com.zhu.service.CourseService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl  implements CourseService {
    private static Logger logger=Logger.getLogger(CourseService.class);
    @Autowired
    private CourseDao courseDao;
    @Override
    public  List<Course>  selectCourseName(){
        try{
            logger.info("查询所有科目开始");
            List<Course> courseName = courseDao.selectCourseName();
            logger.info("查询所有科目结束，查询成功");
            return courseName;
        }catch (Exception e) {
            logger.error("出现异常，查询失败");
            e.printStackTrace();
            return null;
        }

    }
}
