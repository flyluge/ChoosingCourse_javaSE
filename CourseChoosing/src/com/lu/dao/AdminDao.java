package com.lu.dao;

import com.lu.domain.Admin;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 管理员数据操作类
 * @Author: Luge
 * @DATE: 2019/8/21 15:01
 */
public class AdminDao extends BaseDao{
    public AdminDao(String path){
        super(path);
    }
    public boolean add(Admin admin){
        //判断文件是否存在
        Map map=null;
        if(new File(serlizablePath).exists()){
            map= (Map<?,?>)reSerializableObj();
            //检查管理员账号是否已经存在
            for (Object s:map.keySet()) {
                Admin a = (Admin)map.get(s);
                if(admin.getNum().equals(a.getNum())){
                    System.out.println("账号已经存在!");
                    return false;
                }
            }
        }else{
            map=new HashMap<String,Admin>();
        }
        //将管理员信息存入map中
        admin.setAid(UUID.randomUUID().toString().replace("-",""));
        map.put(admin.getAid(),admin);
        serializableObj(map);
        return true;
    }

    /**
     * 通过id删除
     * @param id
     * @return
     */
    public boolean delete(Serializable id){
        //判断文件是否存在
        if(new File(serlizablePath).exists()){
            Map map= (Map<?,?>)reSerializableObj();
            for(Object obj:map.keySet()){
                Admin admin= (Admin) map.get(obj);
                //查找与账号密码对应的admin
                if(admin.getAid().equals(id)){
                    map.remove(obj);
                    serializableObj(map);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 更新用户
     * 传入的参数admin需要带有主码
     * @param admin
     * @return
     */
    public boolean update(Admin admin){
        //判断文件是否存在
        if(new File(serlizablePath).exists()){
            Map map= (Map<?,?>)reSerializableObj();
            Admin a=(Admin)map.get(admin.getAid());
            //查询要更新的对象是否存在
            if(a!=null){
                //存在更新对象
                map.remove(admin.getAid());
                map.put(admin.getAid(),admin);
                serializableObj(map);
            }else{
                //不存在返回false
                return false;
            }
        }
        return false;
    }

    /**
     * 通过id获取Admin
     * @param id
     * @return
     */
    public Admin findAdminById(Serializable id){
        //判断文件是否存在
        if(new File(serlizablePath).exists()){
            Map map= (Map<?,?>)reSerializableObj();
            Admin admin= (Admin) map.get(id);
            if(admin!=null){
                return admin;
            }
        }
        //文件不存在或没有找到对应的值 返回false
        return null;
    }
    /**
     * 通过账号密码获取管理员对象
     * @return
     */
    public Admin findByNumAdnPassword(String num,String password){
        //判断文件是否存在
        if(new File(serlizablePath).exists()){
            Map map= (Map<?,?>)reSerializableObj();
            for(Object obj:map.keySet()){
                Admin admin= (Admin) map.get(obj);
                //查找与账号密码对应的admin
                if(admin.getNum().equals(num)&&admin.getPassword().equals(password)){
                    return admin;
                }
            }
        }
        //文件不存在或没有找到对应的值 返回false
        return null;
    }
}
