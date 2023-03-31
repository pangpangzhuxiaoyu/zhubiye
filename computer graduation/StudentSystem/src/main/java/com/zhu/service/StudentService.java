package com.zhu.service;

import com.zhu.domain.Student;
import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
public interface StudentService {
    /**
     * 查询全部
     */
    public List<Student> selectAll();
}
