package com.zhu.service;

import com.zhu.pojo.PageBean;
import com.zhu.pojo.PojoByCondition;
import com.zhu.domain.Student;
import com.zhu.pojo.StudentWithScore;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
public interface StudentService {
    /**
     * 根据页数信息查询符合条件的数据（分页查询）
     * @param CurPage 当前所在页数
     * @param pageSize  每页显示的条数
     * @param pojoByCondition  条件查询的所有条件信息
     * @return
     */
    public PageBean<Student> selectAllByPageWithCondition(int CurPage, int pageSize, PojoByCondition pojoByCondition);




    public boolean deleteById(Integer studentId);

    /**
     * 根据学生的id批量删除
     * @param studentIds
     * @return
     */
    public boolean deleteByIds(int[] studentIds);

    /**
     * 添加学生信息以及成绩信息
     * @param student
     * @param studentWithScoreList
     * @return
     */
    public boolean studentAdd(Student student, List<StudentWithScore> studentWithScoreList);

    /**
     * 修改学生信息异界成绩信息
     * @param student
     */
    public boolean studentUpdate(Student student);

}
