package com.zhu.dao;

import com.zhu.pojo.PojoByCondition;
import com.zhu.domain.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface StudentDao {

    /**
     * 根据页数信息查询符合条件的数据（分页查询）
     * @param begin 开始的页数
     * @param size  每页的条数
     * @param pojoByCondition  条件查询的所有条件信息
     * @return
     */
    public List<Student> selectAllByPageWithCondition(@Param("begin") int begin, @Param("size") int size,
                                         @Param("pojoByCondition") PojoByCondition pojoByCondition);

    /**
     * 用于到处excel全部数据
     * @return
     */
    public List<Student> selectAll();

    /**
     *查询学生总的记录数
     */
    //@Select("select count(*) from student")
    int selectTotalCount( @Param("pojoByCondition") PojoByCondition pojoByCondition);

    /**
     * 用于查询课程的数目
     * @return
     */
    @Select("select count(*) from course")  //因为不涉及到映射所以定义在了student里，这个方法的主要作用是提供给前端又有多少个科目
    int selectCourseNum();

    /*@Delete({"delete from student where student_id=#{student.studentId}",
            "delete from score where score.student_id=#{student.studentId}"})*/
    @Delete("delete from student where student_id=#{studentId}")
    void deleteById(@Param("studentId") Integer studentId);

    /**
     * 根据ID批量删除
     * @param studentIds
     */
    void deleteByIds(@Param("studentIds") int[] studentIds);

    /**
     * 新增学生
     */
    @Insert("INSERT INTO `Student` (`student_id`, `student_name`, `student_gender`, `student_birth`, `student_tel`,`student_adress`) " +
            "VALUES (#{studentId}, #{studentName}, #{studentGender}, #{studentBirth}, #{studentTel}, #{studentAdress})")
    void studentAdd(Student student);

    /**
     * 修改学生信息
     * @param student
     */
    @Update("UPDATE  `Student` SET `student_name`=#{studentName},`student_gender`=#{studentGender},"+
            "`student_birth`=#{studentBirth},`student_tel`=#{studentTel},`student_adress`=#{studentAdress} " +
            "WHERE `student_id`=#{studentId}")
    void studentUpdate(Student student);



}
