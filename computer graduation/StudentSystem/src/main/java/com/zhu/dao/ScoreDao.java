package com.zhu.dao;

import com.zhu.domain.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

public interface ScoreDao {
    @Delete("delete from score where student_id=#{studentId}")
    void deleteById(@Param("studentId") String studentId);

    /**
     * 根据ID批量删除
     * @param studentIds
     */
    void deleteByIds(@Param("studentIds") String[] studentIds);
}
