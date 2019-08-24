package com.lu.domain;

import java.io.Serializable;

/**
 * 课程类
 * @Author: Luge
 * @DATE: 2019/8/21 14:53
 */
public class Course implements Serializable {
	private String cid;//课程主码
    private String courseNum;//课程号
    private String cname;//课程名称
    private String teacher;//任课老师
    private float time;//学时

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }
}
