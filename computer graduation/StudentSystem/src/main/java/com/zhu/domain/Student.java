package com.zhu.domain;



import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;


public class Student {


    //性别在数据库中定义为枚举类型
    public enum Gender {
        男,
        女
    }

    public Student() {
    }

    public Student(Integer studentId, String studentName, Gender studentGender, Date studentBirth, String studentTel, String studentAdress) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentGender = studentGender;
        this.studentBirth = studentBirth;
        this.studentTel = studentTel;
        this.studentAdress = studentAdress;
    }

    public Integer studentId;
    public String studentName;
    public Gender studentGender;
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    public Date studentBirth;
    public String studentTel;
    public String studentAdress;

    public List<Score> scores;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Gender getStudentGender() {
        return studentGender;
    }

    public void setStudentGender(Gender studentGender) {
        this.studentGender = studentGender;
    }

    public Date getStudentBirth() {
        return studentBirth;
    }

    public void setStudentBirth(Date studentBirth) {
        this.studentBirth = studentBirth;
    }

    public String getStudentTel() {
        return studentTel;
    }

    public void setStudentTel(String studentTel) {
        this.studentTel = studentTel;
    }

    public String getStudentAdress() {
        return studentAdress;
    }

    public void setStudentAdress(String studentAdress) {
        this.studentAdress = studentAdress;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }
}
