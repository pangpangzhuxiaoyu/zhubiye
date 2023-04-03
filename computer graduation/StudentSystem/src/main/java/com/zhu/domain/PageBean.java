package com.zhu.domain;

import java.util.List;

/**
 * 这个类的主要作用是在分页时，能够将查询到的总记录数传输到前端，然后显示到页面下方
 * @param <T> PageBean的类型，为了方便程序扩展，所以将其设为泛型
 */
public class PageBean<T> {
    //总记录数
    private int totalCount;
    //页面数据
    private List<T> rowsStudents;

    //科目的个数
    private int courseNum;

    public int getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(int courseNum) {
        this.courseNum = courseNum;
    }

    public int getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getRowsStudents() {
        return rowsStudents;
    }

    public void setRowsStudents(List<T> rowsStudents) {
        this.rowsStudents = rowsStudents;
    }
}
