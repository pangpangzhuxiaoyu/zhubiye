package com.zhu.dao;

import com.zhu.domain.Student;
import com.zhu.domain.StudentWithScore;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScoreDao {
    /**
     * 根据id删除
     * @param studentId
     */
    @Delete("delete from score where student_id=#{studentId}")
    void deleteById(@Param("studentId") Integer studentId);

    /**
     * 根据ID批量删除
     * @param studentIds
     */
    void deleteByIds(@Param("studentIds") int[] studentIds);

    /**
     * 添加学生 时为了保持数据一致性 也要插入成绩信息
     */
    void scoreAdd(List<StudentWithScore> list);
    /**
     * 修改学生 时为了保持数据一致性 也要插入成绩信息
     */
    void scoreUpdate(@Param("item") StudentWithScore studentWithScore);



}
