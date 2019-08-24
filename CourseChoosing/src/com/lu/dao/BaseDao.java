package com.lu.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

/**
 * @Author: Luge
 * @DATE: 2019/8/21 16:17
 */
public class BaseDao{
    /**
     * 序列化文件的地址
     */
    String serlizablePath;
    BaseDao(String path){
        serlizablePath=path;
    }
    /**
     * 反序列化文件
     * 从文件中读取对象
     * @return
     */
    protected  Object reSerializableObj(){
        File file=new File(serlizablePath);
        if(file.exists()){
            try{
                ObjectInputStream oos=new ObjectInputStream(new FileInputStream(file));
                return oos.readObject();
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            System.out.println("序列化的文件不存在");
        }
        return null;
    }
    /**
     * 将对象序列化到文件中
     * @param obj 需要序列化的对象
     */
    protected void serializableObj(Object obj){
        File file=new File(serlizablePath);
        try{
            ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(obj);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 获取反序列化后的Map对象
     * 如果序列化文件不存在 返回null
     * @return
     */
    protected Map getSerializeMap(){
        Map map=null;
        if(new File(serlizablePath).exists()){
            map= (Map<?,?>)reSerializableObj();
        }
        return map;
    }
    /**
     * 获取所有序列化的map
     * @return
     */
    public Map findAll(){
        //判断文件是否存在
        if(new File(serlizablePath).exists()){
            Map map= (Map<?,?>)reSerializableObj();
            return map;
        }
        //文件不存在或没有找到对应的值 返回false
        return null;
    }

}
