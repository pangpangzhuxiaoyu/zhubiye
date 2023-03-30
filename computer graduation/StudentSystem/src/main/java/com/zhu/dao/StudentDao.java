package com.zhu.dao;

import com.zhu.domain.Student;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StudentDao {
    @Select("select * from Student")
    public List<Student> selectAll();
}
