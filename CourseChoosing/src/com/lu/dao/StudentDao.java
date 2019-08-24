package com.lu.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lu.domain.Student;
import com.lu.util.UUIDUtil;

/**
 * 学生DAO
 * @Author: Luge
 * @DATE: 2019/8/21 16:35
 */
public class StudentDao extends BaseDao{
    public StudentDao(String path){
        super(path);
    }
    public boolean add(Student student){
        //判断文件是否存在
        Map map=getSerializeMap();
        if(map!=null){
            //检查学生账号是否已经存在
            for (Object s:map.keySet()) {
                Student a = (Student)map.get(s);
                if(student.getNum().equals(a.getNum())){
                    System.out.println("账号已经存在!");
                    return false;
                }
            }
        }else{
            map=new HashMap<String,Student>();
        }
        //将学生信息存入map中
        student.setStuid(UUIDUtil.getUUIDid());
        map.put(student.getStuid(),student);
        serializableObj(map);
        return true;
    }

    /**
     * 通过id删除
     * @param id
     * @return
     */
    public boolean delete(Serializable id){
        //反序列化文件
        Map map=getSerializeMap();
        if(map!=null){
            for(Object obj:map.keySet()){
                Student student= (Student) map.get(obj);
                if(student.getStuid().equals(id)){
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
     * 传入的参数Student需要带有主码
     * @param student
     * @return
     */
    public boolean update(Student student){
        //判断文件是否存在
        //反序列化文件
        Map map=getSerializeMap();
        if(map!=null){
            Student a=(Student)map.get(student.getStuid());
            //查询要更新的对象是否存在
            if(a!=null){
                //存在更新对象
                map.remove(student.getStuid());
                //判断更新后的学号是否重复
                for(Object obj:map.keySet()){
                    Student s= (Student) map.get(obj);
                    //查找与账号密码对应的Student
                    if(student.getNum().equals(s.getNum())){
                        return false;
                    }
                }
                //学号不重复则将更新的内容添加到map中
                map.put(student.getStuid(),student);
                serializableObj(map);
                return true;
            }else{
                //不存在返回false
                return false;
            }
        }
        return false;
    }

    /**
     * 通过id获取Student
     * @param id
     * @return
     */
    public Student findStudentById(Serializable id){
        //判断文件是否存在
        //反序列化文件
        Map map=getSerializeMap();
        if(map!=null){
            Student student= (Student) map.get(id);
            if(student!=null){
                return student;
            }
        }
        //文件不存在或没有找到对应的值 返回false
        return null;
    }
    /**
     * 通过账号密码获取学生对象
     * @return
     */
    public Student findByNumAdnPassword(String num,String password){
        //判断文件是否存在
        Map map=getSerializeMap();
        if(map!=null){
            for(Object obj:map.keySet()){
                Student student= (Student) map.get(obj);
                //查找与账号密码对应的Student
                if(student.getNum().equals(num)&&student.getPassword().equals(password)){
                    return student;
                }
            }
        }
        //文件不存在或没有找到对应的值 返回false
        return null;
    }
    /**
     * 通过学号删除学生
     * @param num
     * @return
     */
    public boolean deleteByNum(String num) {
        //判断文件是否存在
        Map map=getSerializeMap();
        if(map!=null){
            //查询要删除的对象是否存在
            for (Object obj:map.keySet()) {
                Student s=(Student) map.get(obj);
                if(s.getNum().equals(num)){
                	//级联删除选课表里的学学生
                	new ChooseCourseDao("choosecourse.txt").deleteByStuId(s.getStuid());
                    map.remove(obj);
                    serializableObj(map);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 通过学号查询学生
     * @param num
     * @return
     */
    public Student findStudentByNum(String num) {
        //判断文件是否存在
        Map map=getSerializeMap();
        if(map!=null){
            //查询要num学号对应的对象
            for (Object obj:map.keySet()) {
                Student s=(Student) map.get(obj);
                if(s.getNum().equals(num)){
                    return s;
                }
            }
        }
        return null;
    }

    /**
     * 通过name模糊查询学生
     * @param name
     * @return
     */
    public List findByName(String name) {
        //判断文件是否存在
        Map map=getSerializeMap();
        List<Student> list=new ArrayList<Student>();
        if(map!=null){
            for (Object obj:map.keySet()) {
                Student s=(Student) map.get(obj);
                if(s.getStuname().contains(name)){
                    list.add(s);
                }
            }
        }
        return list;
    }
}
