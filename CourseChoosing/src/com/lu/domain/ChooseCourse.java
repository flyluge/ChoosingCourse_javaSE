package com.lu.domain;

import java.io.Serializable;

/**
 * @Author: Luge
 * @DATE: 2019/8/21 14:57
 */
public class ChooseCourse implements Serializable {
	private String ccid; //主码
    private Course course;//学生
    private Student student;//课程

    public String getCcid() {
        return ccid;
    }
    
    public void setCcid(String ccid) {
        this.ccid = ccid;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
