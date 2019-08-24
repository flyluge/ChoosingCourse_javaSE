package com.lu.service;

import com.lu.dao.AdminDao;
import com.lu.domain.Admin;

/**
 * @Author: Luge
 * @DATE: 2019/8/21 15:29
 */
public class AdminService {
    AdminDao adminDao=new AdminDao("admin.txt");

    /**
     * 添加管理员
     * @param admin
     */
    public boolean add(Admin admin){
        return adminDao.add(admin);
    }

    /**
     * 通过账号密码获取管理员
     * @return 未找到返回null  找到返回Admin对象
     */
    public Admin findByNumAndPass(String num,String password){
        return adminDao.findByNumAdnPassword(num,password);
    }

    /**
     * 更新admin信息
     * @param admin
     * @return
     */
    public boolean update(Admin admin){
        return adminDao.update(admin);
    }
}
