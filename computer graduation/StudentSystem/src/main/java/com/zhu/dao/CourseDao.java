package com.zhu.dao;

import com.zhu.domain.Course;
import com.zhu.domain.Student;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CourseDao {

    @Select(" SELECT Course.course_name,Course.course_id FROM Course ")
    @ResultMap("courseResultMap")
    public List<Course> selectCourseName();
}
