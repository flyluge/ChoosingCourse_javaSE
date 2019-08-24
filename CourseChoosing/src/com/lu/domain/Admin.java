package com.lu.domain;

import java.io.Serializable;

/**
 * @Author: Luge
 * @DATE: 2019/8/21 14:59
 */
public class Admin implements Serializable {
	private String aid;//管理员id 主码
    private String num;//管理员账号  唯一
    private String name;//管理员名称
    private String password;//管理员密码

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
