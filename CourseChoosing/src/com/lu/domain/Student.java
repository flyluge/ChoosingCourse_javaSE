package com.lu.domain;

import java.io.Serializable;

/**
 * 学生类
 * @Author: Luge
 * @DATE: 2019/8/21 14:48
 */
public class Student implements Serializable {

	private String stuid;//编号 主码
    private String stuname;//学生姓名
    private String num;//学号
    private String password;//密码
    private String sex;//性别

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid;
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
