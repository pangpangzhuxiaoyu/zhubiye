package com.zhu.dao;

import com.zhu.domain.Student;
import org.apache.ibatis.annotations.Select;

public interface StudentDao {
    @Select("select * from Student")
    public List<Student> sellectAll();
}
