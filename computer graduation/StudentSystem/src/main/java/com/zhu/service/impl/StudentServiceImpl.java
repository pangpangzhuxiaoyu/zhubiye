package com.zhu.service.impl;

import com.zhu.dao.ScoreDao;
import com.zhu.dao.StudentDao;
import com.zhu.dao.CourseDao;
import com.zhu.domain.PageBean;
import com.zhu.domain.PojoByCondition;
import com.zhu.domain.Student;
import com.zhu.domain.StudentWithScore;
import com.zhu.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private ScoreDao scoreDao;



//    @Override
//    public PageBean<Student> selectAllByPage(int curPage, int pageSize) {
//        //查询开始的索引为当前页数-1后乘页面的条数
//        int begin =(curPage-1)*pageSize;
//        //计算查询条数
//        int size =pageSize;
//        //获取符合条件的行
//        List<Student> rows = studentDao.selectAllByPage(begin,size);
//        int count =studentDao.selectTotalCount();
//        int courseNum=studentDao.selectCourseNum();
//        //将所有符合条件的行以及总学生数据set进PageBean里面
//        PageBean<Student> pageBean=new PageBean<>();
//        pageBean.setRowsStudents(rows);
//        pageBean.setTotalCount(count);
//        pageBean.setCourseNum(courseNum);
//        return pageBean;
//    }
@Override
public PageBean<Student> selectAllByPageWithCondition(int curPage, int pageSize, PojoByCondition pojoByCondition) {
    //查询开始的索引为当前页数-1后乘页面的条数
    int begin = (curPage - 1) * pageSize;
    //计算查询条数
    int size = pageSize;
    //判断学生姓名，因为要做模糊查询
    String studentNameString= pojoByCondition.studentName;
    if(studentNameString !=null&&studentNameString.length()>0){
        pojoByCondition.setStudentName("%"+studentNameString+"%");
    }
    //获取符合条件的行
    List<Student> rows = studentDao.selectAllByPageWithCondition(begin, size,pojoByCondition);
    int count = studentDao.selectTotalCount(pojoByCondition);
    int courseNum = studentDao.selectCourseNum();
    //将所有符合条件的行以及总学生数据set进PageBean里面
    PageBean<Student> pageBean = new PageBean<>();
    pageBean.setRowsStudents(rows);
    pageBean.setTotalCount(count);
    pageBean.setCourseNum(courseNum);
    return pageBean;
}


    @Override
    public boolean deleteById(Integer studentId) {
        //删除两张表 保持数据一致性
        studentDao.deleteById(studentId);
        scoreDao.deleteById(studentId);
        return true;
    }

    @Override
    public boolean deleteByIds(int[] studentIds) {
        //删除两张表 保持数据一致性
        studentDao.deleteByIds(studentIds);
        scoreDao.deleteByIds(studentIds);
        return true;
    }
    @Override
    public boolean studentAdd(Student student, List<StudentWithScore> studentWithScoreList){
        //将学生的id set到插入成绩的临时类中 以便于在插入成绩时可以直接获得
        for(StudentWithScore studentWithScore:studentWithScoreList){
            studentWithScore.setStudentId(student.studentId);
        }
        //执行插入学生
        studentDao.studentAdd(student);
        //执行插入成绩
        scoreDao.scoreAdd(studentWithScoreList);
        return true;
    }
    @Override
    public boolean studentUpdate(Student student){
        List<StudentWithScore> studentWithScoreList=new ArrayList<>();
        for (int i=0;i<student.scores.size();i++){
            StudentWithScore studentWithScore = new StudentWithScore();
            studentWithScore.setStudentId(student.getStudentId());
            studentWithScore.setCourseId(student.scores.get(i).course.getCourseId());
            studentWithScore.setScore(student.scores.get(i).getSubjectScore());
            studentWithScoreList.add(studentWithScore);
        }
        studentDao.studentUpdate(student);
        for(StudentWithScore studentWithScore:studentWithScoreList){
            scoreDao.scoreUpdate(studentWithScore);
        }

        return true;
    }

}
