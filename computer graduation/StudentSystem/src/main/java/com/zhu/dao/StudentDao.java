package com.zhu.dao;

import com.zhu.domain.Student;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public interface StudentDao {


    public List<Student> selectAll();
}
