package com.zhu.service;

import com.zhu.domain.PageBean;
import com.zhu.domain.Student;
import com.zhu.domain.StudentWithScore;
import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
public interface StudentService {
    /**
     *
     * @param CurPage 用户当前所在页数
     * @param pageSize 用户当前每页显示条数
     * @return
     */
    public PageBean<Student> selectAllByPage(int CurPage, int pageSize);


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

}
