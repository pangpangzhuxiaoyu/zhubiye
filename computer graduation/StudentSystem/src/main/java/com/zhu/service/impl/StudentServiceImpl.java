package com.zhu.service.impl;

import com.zhu.dao.ScoreDao;
import com.zhu.dao.StudentDao;
import com.zhu.pojo.PageBean;
import com.zhu.pojo.PojoByCondition;
import com.zhu.domain.Student;
import com.zhu.pojo.StudentWithScore;
import com.zhu.service.StudentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private static Logger logger=Logger.getLogger(StudentService.class);
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
    try{
        logger.info("查询所有开始");
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
        logger.info("查询所有结束");
        return pageBean;

    }catch(Exception e){
        e.printStackTrace();
        logger.info("出现异常，查询失败");
        return null;

    }
}



    @Override
    public boolean deleteById(Integer studentId) {
        try{
            logger.info("删除开始");
            //删除两张表 保持数据一致性
            studentDao.deleteById(studentId);
            scoreDao.deleteById(studentId);
            logger.info("删除结束，删除成功");
            return true;
        }catch(Exception e){
            e.printStackTrace();
            logger.error("出现异常，删除失败，事务回滚");
            return false;
        }
    }

    @Override
    public boolean deleteByIds(int[] studentIds) {
        try{
            logger.info("批量删除开始");
            //删除两张表 保持数据一致性
            studentDao.deleteByIds(studentIds);
            scoreDao.deleteByIds(studentIds);
            logger.info("批量删除结束，删除成功");
            return true;
        }catch(Exception e){
            logger.error("删除出现异常，事务回滚");
            e.printStackTrace();
            return false;
        }

    }
    @Override
    public boolean studentAdd(Student student, List<StudentWithScore> studentWithScoreList){
        try{
            logger.info("新增学生开始");
            //将学生的id set到插入成绩的临时类中 以便于在插入成绩时可以直接获得
            for(StudentWithScore studentWithScore:studentWithScoreList){
            studentWithScore.setStudentId(student.studentId);
            }
            //执行插入学生
            studentDao.studentAdd(student);
            //执行插入成绩
            scoreDao.scoreAdd(studentWithScoreList);
            logger.info("新增学生结束，新增成功");
            return true;
        }catch(Exception e){
            logger.error("出现异常，新增失败，事务回滚");
            e.printStackTrace();
            return false;
        }

    }
    @Override
    public boolean studentUpdate(Student student){
        try{
            logger.info("更新开始");
            if(student.getStudentId() == null || student.getStudentId() <= 0){
                throw new IllegalArgumentException("学生ID不能为空或小于等于0！");
            }
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
            logger.info("更新结束，更新成功");
            return true;
        }catch(Exception e){
            logger.error("出现异常，更新失败，事务回滚");
            e.printStackTrace();
            return false;
        }
    }

}
