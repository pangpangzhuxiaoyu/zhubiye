package com.zhu.service;

import com.zhu.domain.Course;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface CourseService  {

    /**
     * 查询课程的名字
     */
    public List<Course>  selectCourseName();
}
