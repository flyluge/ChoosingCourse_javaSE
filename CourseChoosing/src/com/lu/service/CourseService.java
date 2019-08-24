package com.lu.service;

import java.util.List;
import java.util.Map;

import com.lu.dao.CourseDao;
import com.lu.domain.Course;

/**
 * @Author: Luge
 * @DATE: 2019/8/22 10:15
 */
public class CourseService {
    CourseDao courseDao=new CourseDao("course.txt");

    public List<?> findByName(String name) {
        return courseDao.findByName(name);
    }

    public Map<?, ?> findAll() {
        return courseDao.findAll();
    }

    public boolean add(Course course) {
        return courseDao.add(course);
    }

    public boolean deleteCourseByNum(String num) {
        return courseDao.deleteByNum(num);
    }

    public Course findCourseByNum(String num) {
        return courseDao.findCourseByNum(num);
    }

    public boolean updateCourse(Course course) {
        return courseDao.update(course);
    }
}
