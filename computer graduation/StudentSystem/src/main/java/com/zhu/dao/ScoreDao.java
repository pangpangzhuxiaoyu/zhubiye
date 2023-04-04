package com.zhu.dao;

import com.zhu.domain.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

public interface ScoreDao {
    @Delete("delete from score where student_id=#{student.studentId}")
    void deleteById(@Param("student") Student student);
}
