package com.lu.dao;

import com.lu.domain.Course;
import com.lu.util.UUIDUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Luge
 * @DATE: 2019/8/22 9:53
 */
public class CourseDao extends BaseDao {
    public CourseDao(String path) {
        super(path);
    }

    /**
     * 添加课程
     * 如果课程id存在 返回false
     * @param course
     * @return
     */
    public boolean add(Course course){
        //判断文件是否存在
        Map map=getSerializeMap();
        if(map!=null){
            //检查课程账号是否已经存在
            for (Object s:map.keySet()) {
                Course a = (Course)map.get(s);
                if(course.getCourseNum().equals(a.getCourseNum())){
                    System.out.println("课程号已经存在!");
                    return false;
                }
            }
        }else{
            map=new HashMap<String,Course>();
        }
        //将课程信息存入map中
        course.setCid(UUIDUtil.getUUIDid());
        map.put(course.getCid(),course);
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
                Course course= (Course) map.get(obj);
                if(course.getCid().equals(id)){
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
     * 传入的参数Course需要带有主码
     * 如果更新后的课程号重复 则返回false
     * @param course
     * @return
     */
    public boolean update(Course course){
        //判断文件是否存在
        //反序列化文件
        Map map=getSerializeMap();
        if(map!=null){
            Course a=(Course)map.get(course.getCid());
            //查询要更新的对象是否存在
            if(a!=null){
                //存在更新对象
                map.remove(course.getCid());
                //判断更新后的学号是否重复
                for(Object obj:map.keySet()){
                    Course s= (Course) map.get(obj);
                    //查找与账号密码对应的Course
                    if(course.getCourseNum().equals(s.getCourseNum())){
                        return false;
                    }
                }
                //学号不重复则将更新的内容添加到map中
                map.put(course.getCid(),course);
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
     * 通过id获取Course
     * @param id
     * @return
     */
    public Course findCourseById(Serializable id){
        //判断文件是否存在
        //反序列化文件
        Map map=getSerializeMap();
        if(map!=null){
            Course course= (Course) map.get(id);
            if(course!=null){
                return course;
            }
        }
        //文件不存在或没有找到对应的值 返回false
        return null;
    }
    /**
     * 通过课程号删除课程
     * @param num
     * @return
     */
    public boolean deleteByNum(String num) {
        //判断文件是否存在
        Map map=getSerializeMap();
        if(map!=null){
            //查询要删除的对象是否存在
            for (Object obj:map.keySet()) {
                Course s=(Course) map.get(obj);
                if(s.getCourseNum().equals(num)){
                	new ChooseCourseDao("choosecourse.txt").deleteByCourseId(s.getCid());
                    map.remove(obj);
                    serializableObj(map);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 通过课程号查询课程
     * @param num
     * @return
     */
    public Course findCourseByNum(String num) {
        //获取map
        Map map=getSerializeMap();
        if(map!=null){
            //查询要num学号对应的对象
            for (Object obj:map.keySet()) {
                Course s=(Course) map.get(obj);
                if(s.getCourseNum().equals(num)){
                    return s;
                }
            }
        }
        return null;
    }

    /**
     * 通过name模糊查询课程
     * @param name
     * @return
     */
    public List findByName(String name) {
        //判断文件是否存在
        Map map=getSerializeMap();
        List<Course> list=new ArrayList<Course>();
        if(map!=null){
            for (Object obj:map.keySet()) {
                Course s=(Course) map.get(obj);
                if(s.getCname().contains(name)){
                    list.add(s);
                }
            }
        }
        return list;
    }
}
