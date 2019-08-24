package com.lu.service;

import com.lu.dao.StudentDao;
import com.lu.domain.Student;

import java.util.List;
import java.util.Map;

/**
 * @Author: Luge
 * @DATE: 2019/8/21 17:29
 */
public class StudentService {
    StudentDao studentDao=new StudentDao("student.txt");
    /**
     * 获取所有学生信息
     * @return
     */
    public Map<?, ?> findAll(){
        return studentDao.findAll();
    }

    public boolean add(Student student) {
        return studentDao.add(student);
    }

    public boolean deleteStuByNum(String num) {
        return studentDao.deleteByNum(num);
    }

    public Student findStuByNum(String num) {
        return studentDao.findStudentByNum(num);
    }

    public boolean updateStu(Student student) {
        return studentDao.update(student);
    }

    public List<?> findByName(String name) {
        return studentDao.findByName(name);
    }

    public Student findByNumAndPass(String num, String password) {
        return studentDao.findByNumAdnPassword(num,password);
    }
}
