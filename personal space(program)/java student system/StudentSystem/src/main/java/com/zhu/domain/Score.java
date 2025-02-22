package com.zhu.domain;

import java.util.List;

public class Score {
    public Integer studentId;
    public Integer courseId;
    public Double subjectScore = null ;

    public Course course;

    public void setCourse(Course course) {
        this.course = course;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Double getSubjectScore() {
        return subjectScore;
    }

    public void setSubjectScore(Double subjectScore) {
        this.subjectScore = subjectScore;
    }

    public Course getCourse() {
        return course;
    }
}
