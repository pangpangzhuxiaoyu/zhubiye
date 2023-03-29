package com.zhu.service;

import org.apache.ibatis.annotations.Select;

public interface StudentService {
    /**
     * 查询全部
     */
    public List<Student> sellectAll();
}
