package com.zhu.dao;

import com.zhu.domain.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public interface StudentDao {

    /**
     * 根据页数信息查询符合条件的数据（分页查询）
     * @param begin 开始的页数
     * @param size  每页的条数
     * @return
     */
    public List<Student> selectAllByPage(@Param("begin") int begin,@Param("size") int size);

    /**
     *查询学生总的记录数
     */
    @Select("select count(*) from student")
    int selectTotalCount();

    /**
     * 用于查询课程的数目
     * @return
     */
    @Select("select count(*) from course")  //因为不涉及到映射所以定义在了student里，这个方法的主要作用是提供给前端又有多少个科目
    int selectCourseNum();
    @Delete({"delete from student where student_id=#{studentId}","delete from score where student_id=#{studentId}"})
    void deleteById(Student student);


}
