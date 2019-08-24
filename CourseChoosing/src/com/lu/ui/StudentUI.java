package com.lu.ui;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.lu.domain.ChooseCourse;
import com.lu.domain.Course;
import com.lu.domain.Student;
import com.lu.service.ChooseCourseService;
import com.lu.service.StudentService;

/**
 * @Author: Luge
 * @DATE: 2019/8/22 10:55
 */
public class StudentUI {
    static final StudentService studentService=new StudentService();
    static final ChooseCourseService ccs=new ChooseCourseService();
    static Student student=null;
    static final Scanner in=new Scanner(System.in);
    /**
           * 登陆界面
     */
    public static void loginUI(){
        while(true){
            System.out.println("欢迎来到学生界面,请先登陆");
            String num;
            String password;
            System.out.println("请输入账号:(输入exit返回到主界面)");
            num=in.next();
            if(num.equals("exit")){
                MainFrameUI.showMainFrame();
                return;
            }
            System.out.println("请输入密码:");
            password=in.next();
            student = studentService.findByNumAndPass(num, password);
            if(student!=null){
                studentSelectUI();
                break;
            }else{
                System.out.println("用户名或密码不正确,请重新登陆");
            }
        }
    }

    /**
            * 学生主界面
     */
    public static void studentSelectUI(){
        System.out.println("欢迎"+student.getStuname());
        System.out.println("请选择要进行的操作");
        System.out.println("0. 退出");
        System.out.println("1. 选择课程");
        System.out.println("2. 查询可选课程");
        System.out.println("3. 查询已选课程");
        System.out.println("4. 修改选课");
        System.out.println("5. 修改密码");
        String num=in.next();
        switch (num){
            case "0":MainFrameUI.showMainFrame(); break;
            case "1":selectCourseUI(); break;
            case "2":showCanSelectCourse(); break;
            case "3":showSelectedCourse(); break;
            case "4":updateChooseCourse(); break;
            case "5":updatePassword(); break;
            default:
                student=null;
                loginUI();
                break;
        }
    }

    /**
     * 显示可选课程
     */
    public static void showCanSelectCourse(){
        System.out.println("当前位置:学生主页->查询可选课程");
        System.out.println("可选择的课程如下");
        Map<?, ?> map=ccs.getCanSelectCourse(student);
        if(map.size()==0){
            System.out.println("已经没有课程可供选择了!");
        }else{
            System.out.printf("%10s%10s%10s%10s\n","课程号","课程名称","任课教师","学时");
            for(Object obj:map.keySet()){
                Course course=(Course) map.get(obj);
                System.out.printf("%10s%10s%10s%10s\n",course.getCourseNum(),course.getCname(),course.getTeacher(),course.getTime());
            }
            System.out.println();
        }
        System.out.println("按0返回上一层 按1进入选择课程界面");
        switch (in.next()){
            case "1":selectCourseUI();break;
            case "0":studentSelectUI();break;
            default:showCanSelectCourse();break;
        }
    }
    /**
     * 显示已选课程
     */
    public static void showSelectedCourse(){
        System.out.println("当前位置:学生主页->查询已选课程");
        System.out.println("已经选择的课程如下");
        List<Course> list=ccs.getAllSelectedCourse(student);
        if(list.size()==0){
            System.out.println("您还未选择课程,请先选取课程!");
        }else{
        	 System.out.printf("%10s%10s%10s%10s\n","课程号","课程名称","任课教师","学时");
            for (Course course:list) {
            	System.out.printf("%10s%10s%10s%10s\n",course.getCourseNum(),course.getCname(),course.getTeacher(),course.getTime());
            }
            System.out.println();
        }
        System.out.println("按0返回上一层");
        if(in.next().equals("0")){
            studentSelectUI();
        }else{
            showSelectedCourse();
        }
    }

    /**
     * 选择课程
     */
    public static void selectCourseUI(){
        System.out.println("当前位置:学生主页->选择课程");
        System.out.println("请输入课程的编号:(按0返回到上一层)");
        String num=in.next();
        if(num.equals("0")){
            studentSelectUI();
            return;
        }
        if(ccs.selectCourse(student,num)){
            System.out.println("选取成功!(按1继续选取 按0返回上一层)");
            switch (in.next()){
                case "1":selectCourseUI();break;
                case "0":studentSelectUI();break;
                default:selectCourseUI();break;
            }
        }else{
            System.out.println("所选课程不存在或已被选取");
            selectCourseUI();
        }
    }

    /**
     * 修改选课
     */
    public static void updateChooseCourse(){
        System.out.println("当前位置:学生主页->修改选课");
        System.out.println("已经选择的课程如下");
        List<Course> list=ccs.getAllSelectedCourse(student);
        if(list.size()==0){
            System.out.println("您还未选择课程,请先选取课程!");
        }else{
        	 System.out.printf("%10s%10s%10s%10s\n","课程号","课程名称","任课教师","学时");
            for (Course course:list) {
            	System.out.printf("%10s%10s%10s%10s\n",course.getCourseNum(),course.getCname(),course.getTeacher(),course.getTime());
            }
            System.out.println();
        }
        System.out.println("请输入要修改的课程的编号:(按0返回到上一层)");
        String num=in.next();
        if(num.equals("0")){
            studentSelectUI();
            return;
        }
        ChooseCourse c=ccs.findChooseCourse(student,num);
        if(c==null){
            System.out.println("您还没有选择该课程");
            updateChooseCourse();
            return;
        }else{
            System.out.println("请选择要执行的操作");
            System.out.println("1. 删除该课程 2. 更改");
            switch (num=in.next()){
                case "1":
                    ccs.deleteChooseCourse(c.getCcid());
                    System.out.println("删除成功!");
                    updateChooseCourse();
                    return;
                case "2":
                    System.out.println("请输入修改后的课程号");
                    num=in.next();
                    if(ccs.updateChooseCourse(c,num)){
                        System.out.println("修改成功");
                    }else{
                        System.out.println("修改后的课程号重复了!");
                    }
                    System.out.println("按0返回上一层");
                    switch (in.next()){
                        case "0":studentSelectUI();break;
                        default:selectCourseUI();break;
                    }

                default:updateChooseCourse();break;
            }
        }
    }

    /**
     * 更改密码
     */
    public static void updatePassword(){
        System.out.println("当前位置:学生主页->修改密码");
        System.out.println("请输入原密码:(按0返回上一层)");
        String password=in.next();
        if(password.equals("0")){
            studentSelectUI();
            return;
        }
        if(student.getPassword().equals(password)){
            System.out.println("密码正确,请输入新密码");
            password=in.next();
            student.setPassword(password);
            if(studentService.updateStu(student)){
                System.out.println("修改成功!(按0返回上一层)");
                switch (in.next()){
                    case "0":studentSelectUI();break;
                    default:studentSelectUI();break;
                }
            }
        }else{
            System.out.println("密码不正确,请重新输入!");
            updatePassword();
        }
    }
}
