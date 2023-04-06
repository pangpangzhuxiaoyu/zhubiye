package com.zhu.service.impl;

import com.zhu.dao.ScoreDao;
import com.zhu.dao.StudentDao;
import com.zhu.domain.PageBean;
import com.zhu.domain.Student;
import com.zhu.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private ScoreDao scoreDao;

    @Override
    public PageBean<Student> selectAllByPage(int curPage, int pageSize) {
        //查询开始的索引为当前页数-1后乘页面的条数
        int begin =(curPage-1)*pageSize;
        //计算查询条数
        int size =pageSize;
        //获取符合条件的行
        List<Student> rows = studentDao.selectAllByPage(begin,size);
        int count =studentDao.selectTotalCount();
        int courseNum=studentDao.selectCourseNum();
        //将所有符合条件的行以及总学生数据set进PageBean里面
        PageBean<Student> pageBean=new PageBean<>();
        pageBean.setRowsStudents(rows);
        pageBean.setTotalCount(count);
        pageBean.setCourseNum(courseNum);
        return pageBean;
    }

    @Override
    public boolean deleteById(String studentId) {
        studentDao.deleteById(studentId);
        scoreDao.deleteById(studentId);
        return true;
    }

    @Override
    public boolean deleteByIds(String[] studentIds) {
        //删除两张表 保持数据一致性
        studentDao.deleteByIds(studentIds);
        scoreDao.deleteByIds(studentIds);
        return true;
    }

}
