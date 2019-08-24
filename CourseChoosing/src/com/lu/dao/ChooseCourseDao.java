package com.lu.dao;

import com.lu.domain.ChooseCourse;
import com.lu.domain.Course;
import com.lu.domain.Student;
import com.lu.util.UUIDUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 学生选课dao
 * @Author: Luge
 * @DATE: 2019/8/22 10:47
 */
public class ChooseCourseDao extends BaseDao {
    public ChooseCourseDao(String path) {
        super(path);
    }
    /**
     * 添加选课
     * 如果选课id存在 返回false
     *
     * @param chooseCourse
     * @return
     */
    public boolean add(ChooseCourse chooseCourse) {
        //判断文件是否存在
        Map map = getSerializeMap();
        if (map != null) {
            //检查选课账号是否已经存在
            for (Object s : map.keySet()) {
                ChooseCourse a = (ChooseCourse) map.get(s);
                if (chooseCourse.getCourse().getCid().equals(a.getCourse().getCid()) && chooseCourse.getStudent().getStuid().equals(a.getStudent().getStuid())) {
                    System.out.println("当前课程已经选过");
                    return false;
                }
            }
        } else {
            map = new HashMap<String, ChooseCourse>();
        }
        //将选课信息存入map中
        chooseCourse.setCcid(UUIDUtil.getUUIDid());
        map.put(chooseCourse.getCcid(), chooseCourse);
        serializableObj(map);
        return true;
    }

    /**
     * 更新选课信息
     * @param chooseCourse 带有id的新的选课信息
     * @return 如果更新后的选课信息重复 则返回false
     */
    public boolean update(ChooseCourse chooseCourse) {
        //判断文件是否存在
        //反序列化文件
        Map map = getSerializeMap();
        if (map != null) {
            ChooseCourse a = (ChooseCourse) map.get(chooseCourse.getCcid());
            //查询要更新的对象是否存在
            if (a != null) {
                //存在更新对象
                map.remove(a.getCcid());
                //判断更新后的学号是否重复
                for (Object obj : map.keySet()) {
                    ChooseCourse s = (ChooseCourse) map.get(obj);
                    //查找与课程号学号对应的ChooseCourse
                    if (chooseCourse.getCourse().getCid().equals(s.getCourse().getCid()) && chooseCourse.getStudent().getStuid().equals(s.getStudent().getStuid())) {
                        return false;
                    }
                }
                //学号不重复则将更新的内容添加到map中
                map.put(chooseCourse.getCcid(), chooseCourse);
                serializableObj(map);
                return true;
            } else {
                //不存在返回false
                return false;
            }
        }
        return false;
    }

    /**
     * 通过id获取ChooseCourse
     *
     * @param id
     * @return
     */
    public ChooseCourse findChooseCourseById(Serializable id) {
        //判断文件是否存在
        //反序列化文件
        Map map = getSerializeMap();
        if (map != null) {
            ChooseCourse chooseCourse = (ChooseCourse) map.get(id);
            if (chooseCourse != null) {
                return chooseCourse;
            }
        }
        //文件不存在或没有找到对应的值 返回false
        return null;
    }

    /**
     * 删除学生选择的课程
     * @param student 选课学生(学生id)
     * @param course 课程(课程id)
     * @return true 删除成功 false 没有选择此课程
     */
    public boolean deleteChooseCourse(Student student,Course course) {
        //判断文件是否存在
        Map map = getSerializeMap();
        if (map != null) {
            //查询要删除的对象是否存在
            for (Object obj : map.keySet()) {
                ChooseCourse s = (ChooseCourse) map.get(obj);
                if (s.getStudent().getStuid().equals(student.getStuid()) && s.getCourse().getCid().equals(course.getCid())) {
                    map.remove(obj);
                    serializableObj(map);
                    return true;
                }
            }
        }
        return false;
    }
    public boolean deleteChooseCourse(String id) {
        //判断文件是否存在
        Map map = getSerializeMap();
        if (map != null) {
            //查询要删除的对象是否存在
            for (Object obj : map.keySet()) {
                ChooseCourse s = (ChooseCourse) map.get(obj);
                if (s.getCcid().equals(id)) {
                    map.remove(obj);
                    serializableObj(map);
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * 通过课程id批量删除选课信息
     * @param id
     * @return
     */
    public boolean deleteByCourseId(String id) {
        //判断文件是否存在
        Map map = getSerializeMap();
        if (map != null) {
            //查询要删除的对象是否存在
        	Set keySet = map.keySet();
        	List<Object> list=new ArrayList<Object>();
        	for (Object obj : keySet) {
				ChooseCourse c = (ChooseCourse) map.get(obj);
				if(c.getCourse().getCid().equals(id)) {
					list.add(obj);
				}
        	}
        	for (Object object : list) {
				map.remove(object);
			}
        	serializableObj(map);
        }
    	return false;
    }
    /**
     * 通过学生id批量删除选课信息
     * @param id
     * @return
     */
    public boolean deleteByStuId(String id) {
        //判断文件是否存在
        Map map = getSerializeMap();
        if (map != null) {
            //查询要删除的对象是否存在
        	Set keySet = map.keySet();
        	List<Object> list=new ArrayList<Object>();
        	for (Object obj : keySet) {
				ChooseCourse c = (ChooseCourse) map.get(obj);
				if(c.getStudent().getStuid().equals(id)) {
					list.add(obj);
				}
        	}
        	for (Object object : list) {
				map.remove(object);
			}
        	serializableObj(map);
        }
    	return false;
    }
    /**
     * 判断学生是否选择对应的课程
     * @return
     */
    public boolean isChooseCourse(Student student,Course course) {
        //获取map
        Map map = getSerializeMap();
        if (map != null) {
            //查询要num学号对应的对象
            for (Object obj : map.keySet()) {
                ChooseCourse s = (ChooseCourse) map.get(obj);
                if (s.getStudent().getStuid().equals(student.getStuid()) && s.getCourse().getCid().equals(course.getCid())) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * 获取已选课程
     * @param student
     * @param course
     * @return
     */
    public ChooseCourse findChooseCourse(Student student,Course course) {
        //获取map
        Map map = getSerializeMap();
        if (map != null) {
            //查询要num学号对应的对象
            for (Object obj : map.keySet()) {
                ChooseCourse s = (ChooseCourse) map.get(obj);
                if (s.getStudent().getStuid().equals(student.getStuid()) && s.getCourse().getCid().equals(course.getCid())) {
                    return s;
                }
            }
        }
        return null;
    }

    /**
     * 获取学生所有已选课程
     * @param student 存有学生的id
     * @return
     */
    public List<Course> findAllChooseCourse(Student student){
        Map map=getSerializeMap();
        List<Course> list=new ArrayList<>();
        if(map!=null){
            for (Object obj:map.keySet()) {
                ChooseCourse c= (ChooseCourse) map.get(obj);
                if(c.getStudent().getStuid().equals(student.getStuid())){
                    list.add(c.getCourse());
                }
            }
        }
        return list;
    }
}