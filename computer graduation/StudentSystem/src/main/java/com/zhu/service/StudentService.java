package com.zhu.service;

import com.zhu.domain.Student;
import org.apache.ibatis.annotations.Select;
import java.util.List;
public interface StudentService {
    /**
     * 查询全部
     */
    public List<Student> selectAll();
}
