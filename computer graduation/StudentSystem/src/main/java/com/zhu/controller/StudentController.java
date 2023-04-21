package com.zhu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.zhu.utils.PageBean;
import com.zhu.pojo.PojoByCondition;
import com.zhu.domain.Student;
import com.zhu.pojo.StudentWithScore;
import com.zhu.service.StudentService;
import com.zhu.utils.Code;
import com.zhu.utils.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/students")
public class StudentController {
    private static Logger logger =Logger.getLogger(StudentController.class);
    @Autowired
    private StudentService studentService;

    @PostMapping("/{condition}") //为了区别于其他的post请求 故要在url上加
    public Result selectAllByPage(@RequestParam("curPage") int curPage,
                                  @RequestParam("pageSize") int pageSize,
                                  @RequestBody String pojo) {
        try {
            logger.info("selectAllByPage()方法被调用");
            // 解析前端传来的 JSON 数据
            com.alibaba.fastjson.JSONObject pojo1 = com.alibaba.fastjson.JSON.parseObject(pojo);
            String pojoByConditionJson = pojo1.getString("pojoByCondition");
            PojoByCondition pojoByCondition = JSON.parseObject(pojoByConditionJson, PojoByCondition.class);

            // 校验最大分数和最小分数是否为数字且在 0-100 之间
            try {
                double maxScore = pojoByCondition.getMaxScore();
                double minScore = pojoByCondition.getMinScore();
                if (maxScore < 0 || maxScore > 100 || minScore < 0 || minScore > 100) {
                    logger.error("最大分数和最小分数必须在0-100之间");
                    throw new IllegalArgumentException("最大分数和最小分数必须在0-100之间");
                }
            } catch (NumberFormatException e) {
                logger.error("最大分数和最小分数必须为数字");
                throw new IllegalArgumentException("最大分数和最小分数必须为数字");
            }

            // 查询学生信息并返回结果
            PageBean<Student> pageBean = studentService.selectAllByPageWithCondition(curPage, pageSize, pojoByCondition);
            Integer code = pageBean.getRowsStudents() != null ? Code.GET_OK : Code.GET_ERROR;
            String msg = pageBean.getRowsStudents() != null ? "" : "数据查询失败";
            logger.info("selectAllByPage()方法调用结束");
            return new Result(code, msg, pageBean);

        } catch (JSONException e) {
            logger.error("转换出现异常");
            // 捕获异常
            return new Result(Code.GET_ERROR);
        }



    }


    @DeleteMapping("/{studentId}")
    public Result deleteById(@PathVariable Integer studentId){
        try {
            logger.info("删除学生按钮,deleteById()方法被调用");
            boolean flag = studentService.deleteById(studentId);
            logger.info("deleteById()方法调用结束");
            return new Result(flag ? Code.DELETE_OK : Code.DELETE_ERROR, flag);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("deleteById()方法出现异常");
            return new Result(Code.DELETE_ERROR);
        }
    }


    //为了区别于单条删除，在请求URL上需要添加Ids
    @DeleteMapping("/Ids/{studentIds}")
    public Result deleteByIds(@PathVariable int[] studentIds) {
        try {
            logger.info("批量删除按钮，deleteByIds()方法被调用");
            //校验传入的studentIds数组是否为空
            if (studentIds == null || studentIds.length == 0) {
                logger.error("studentIds数组不能为空");
                throw new IllegalArgumentException("studentIds数组不能为空");
            }
            //校验数组长度是否超过一定的限制，防止恶意攻击
            if (studentIds.length > 1000) {
                logger.error("studentIds数组长度不能超过1000");
                throw new IllegalArgumentException("studentIds数组长度不能超过1000");
            }
            boolean flag = studentService.deleteByIds(studentIds);
            logger.info("deleteByIds()方法调用结束");
            //返回删除结果
            return new Result(flag ? Code.DELETE_OK : Code.DELETE_ERROR, flag);
        }catch(Exception e) {
            logger.error("出现异常");
            e.printStackTrace();
            return new Result(Code.DELETE_ERROR);
        }
    }

    @PostMapping
    public Result studentAdd(@RequestBody String requestBody){
        try{
            logger.info("新增学生按钮，studentAdd方法被调用");
            //获得前端传来的学生信息
            com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSON.parseObject(requestBody);
            String studentJson = jsonObject.getString("student");
            Student student = JSON.parseObject(studentJson, Student.class);
            //获得前端传来的学生成绩信息*/
            String scores=jsonObject.getString("scores");
            List<StudentWithScore> studentWithScoreList = JSONArray.parseArray(scores,StudentWithScore.class);
            boolean flag = studentService.studentAdd(student,studentWithScoreList);
            logger.info("studentAdd方法调用结束");
            return new Result(flag?Code.SAVE_OK.intValue():Code.SAVE_ERROR,flag);
        }catch(Exception e){
            logger.error("出现异常");
            e.printStackTrace();
            return new Result(Code.SAVE_ERROR);
        }
    }

    @PutMapping
    public Result studentUpdate(@RequestBody String requestBody) {
        try {
            logger.info("编辑学生按钮，studentUpdate方法被调用");
            // 获得前端传来的学生信息
            com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSON.parseObject(requestBody);
            String studentJson = jsonObject.getString("student");
            Student student = JSON.parseObject(studentJson, Student.class);
            boolean flag = studentService.studentUpdate(student);
            logger.info("studentUpdate方法调用结束");
            return new Result(flag ? Code.UPDATE_OK.intValue() : Code.UPDATE_ERROR, flag);
        } catch (Exception e) {
            logger.error("出现异常");
            e.printStackTrace();
            return new Result(Code.UPDATE_ERROR);
        }
    }


}

