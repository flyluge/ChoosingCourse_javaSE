package com.lu.service;

import java.util.List;
import java.util.Map;

import com.lu.dao.ChooseCourseDao;
import com.lu.dao.CourseDao;
import com.lu.domain.ChooseCourse;
import com.lu.domain.Course;
import com.lu.domain.Student;

/**
 * 选课service
 * @Author: Luge
 * @DATE: 2019/8/22 10:46
 */
public class ChooseCourseService {
    ChooseCourseDao chooseCourseDao=new ChooseCourseDao("choosecourse.txt");
    CourseDao courseDao=new CourseDao("course.txt");
    public List<Course> getAllSelectedCourse(Student student) {
        return chooseCourseDao.findAllChooseCourse(student);
    }

    /**
     * 获取能把选取的课程的集合
     * @param student
     * @return
     */
    public Map<?, ?> getCanSelectCourse(Student student) {
        List<Course> list1=chooseCourseDao.findAllChooseCourse(student);
        Map<?, ?> map = courseDao.findAll();
        for (Course c:list1){
            if(map.containsKey(c.getCid())){
                map.remove(c.getCid());
            }
        }
        return map;
    }

    /**
     * 选取课程
     * @param student
     * @param num
     * @return
     */
    public boolean selectCourse(Student student, String num) {

        Course course = courseDao.findCourseByNum(num);
        if(course!=null){
            ChooseCourse chooseCourse=new ChooseCourse();
            chooseCourse.setStudent(student);
            chooseCourse.setCourse(course);
            return chooseCourseDao.add(chooseCourse);
        }
        return false;
    }

    /**
     * 获取选课信息
     * @param student
     * @param num
     * @return
     */
    public ChooseCourse findChooseCourse(Student student, String num) {
        Course course = courseDao.findCourseByNum(num);
        if(course!=null){
            return  chooseCourseDao.findChooseCourse(student,course);
        }
        return null;
    }

    public void deleteChooseCourse(String ccid) {

        chooseCourseDao.deleteChooseCourse(ccid);
    }

    public boolean updateChooseCourse(ChooseCourse c, String num) {
        Course course = courseDao.findCourseByNum(num);
        if(course!=null){
            c.setCourse(course);
            return chooseCourseDao.update(c);
        }
        return false;
    }
}
