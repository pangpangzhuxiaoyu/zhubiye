package com.zhu.service.impl;

import com.zhu.dao.CourseDao;
import com.zhu.domain.Course;
import com.zhu.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl  implements CourseService {
    @Autowired
    private CourseDao courseDao;
    @Override
    public  List<Course>  selectCourseName(){
        List<Course> courseName = courseDao.selectCourseName();
        return courseName;
    }
}
