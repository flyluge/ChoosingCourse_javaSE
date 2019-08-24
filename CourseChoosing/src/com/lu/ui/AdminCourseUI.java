package com.lu.ui;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.lu.domain.Course;
import com.lu.service.CourseService;

/**
 * 管理员课程管理界面
 * @Author: Luge
 * @DATE: 2019/8/22 10:19
 */
public class AdminCourseUI {
    /*输入流*/
    static final Scanner in=new Scanner(System.in);
    static final CourseService service=new CourseService();
    public static void adminCourseUI(){
        System.out.println("当前位置:课程管理");
        System.out.println("请选择要进行的操作");
        System.out.println("0. 返回到管理员主界面");
        System.out.println("1. 查看课程");
        System.out.println("2. 新增课程");
        System.out.println("3. 修改课程");
        System.out.println("4. 删除课程");
        String num=in.next();
        switch (num){
            case "0":AdminUI.adminSelectUI(); break;
            case "1":showCourse(); break;
            case "2":addCourse(); break;
            case "3":updateCourse(); break;
            case "4": deleteCourse();break;
            default:
                AdminUI.adminSelectUI();
        }
    }
    /**
     * 课程信息显示方式的选择列表
     */
    public static void showCourse(){
        System.out.println("当前位置:课程管理->查看课程");
        System.out.println("请选择要进行的操作");
        System.out.println("0. 返回上一层");
        System.out.println("1. 根据课程名称获取信息");
        System.out.println("2. 查看所有课程信息");
        String num=in.next();
        switch (num){
            case "0": adminCourseUI(); break;
            case "1": showOneCourse(); break;
            case "2": showAllCourse();break;
            default:
                adminCourseUI();break;
        }
    }

    /**
     * 根据名称条件显示课程信息
     */
    public static void showOneCourse(){
        System.out.println("当前位置:课程管理->查看课程->根据名称获取课程信息");
        System.out.println("请输入课程名称:(按0返回上一层)");
        String name=in.next();
        if(name.equals("0")){
            showCourse();
            return;
        }
        List<?> list = service.findByName(name);
        if(list.size()==0){
            System.out.println("未查找到课程!");
            showOneCourse();
            return;
        }else{
            System.out.printf("%10s%10s%10s%10s\n","课程名称","课程号","任课老师","学时");
            for (Object obj:list) {
                Course s=(Course)obj;
                System.out.printf("%10s%10s%10s%10s\n",s.getCname(),s.getCourseNum(),s.getTeacher(),s.getTime());
            }
            System.out.println("按1继续查找 按0返回上一层");
            name=in.next();
            switch (name){
                case "0":showCourse();break;
                case "1":showOneCourse();break;
                default:adminCourseUI();break;
            }
        }
    }
    /**
     * 显示所有课程信息
     */
    public static void showAllCourse(){
        System.out.println("当前位置:课程管理->查看课程->所有课程信息");
        System.out.println("所有课程信息如下:");
		Map<?, ?> map = service.findAll();
        if(map==null){
            System.out.println("请选择要进行的操作");
            System.out.println("还未添加课程,请先添加课程(按1跳转到添加课程界面)");
        }else{
        	System.out.printf("%10s%10s%10s%10s\n","课程名称","课程号","任课老师","学时");
            for(Object obj:map.keySet()){
                Course s=(Course) map.get(obj);
                System.out.printf("%10s%10s%10s%10s\n",s.getCname(),s.getCourseNum(),s.getTeacher(),s.getTime());
            }
            System.out.println("请选择要进行的操作");
            System.out.println("按1添加课程 按2删除课程");
        }

        System.out.println("按0返回上一层");
        String num=in.next();
        switch (num){
            case "0":showCourse(); break;
            case "1":addCourse();break;
            case "2":deleteCourse();break;
            default:
                adminCourseUI();
        }
    }

    /**
     * 添加课程信息
     */
    public static void addCourse(){
        System.out.println("当前位置:课程管理->添加课程");
        System.out.println("请输入课程名称:(输入0返回上一层)");
        Course course=new Course();
        String name=in.next();
        course.setCname(name);
        if(name.equals("0")){
            adminCourseUI();
            return;
        }
        System.out.println("请输入课程号:");
        course.setCourseNum(in.next());
        System.out.println("请输入任课老师:");
        course.setTeacher(in.next());
        System.out.println("请输入学时:");
        course.setTime(Float.parseFloat(in.next()));
        if(service.add(course)){
            System.out.println("添加成功,请继续填写课程信息");
            addCourse();
        }else{
            System.out.println("课程号已存在,请重写输入");
            addCourse();
        }
    }

    /**
     * 删除课程信息
     */
    public static void deleteCourse(){
        System.out.println("当前位置:课程管理->删除课程");
        System.out.println("请输入要删除课程的课程号(按0返回上一层)");
        String num=in.next();
        if(num.equals("0")){
            adminCourseUI();
            return;
        }
        if(service.deleteCourseByNum(num)){
            System.out.println("删除成功");
            deleteCourse();
        }else{
            System.out.println("该课程不存在");
            deleteCourse();
        }
    }

    /**
     * 更新课程
     */
    public static void updateCourse(){
        System.out.println("当前位置:课程管理->修改课程");
        System.out.println("请输入要修改课程的课程号(按0返回上一层)");
        String num=in.next();
        if(num.equals("0")){
            adminCourseUI();
            return;
        }
        Course course=service.findCourseByNum(num);
        //修改的课程不存在 重写刷新界面
        if(course==null){
            System.out.println("要修改的课程不存在!");
            updateCourse();
            return;
        }
        System.out.println("要修改课程的信息如下:");
        System.out.printf("%10s%10s%10s%10s\n","课程名称","课程号","任课老师","学时");
        System.out.printf("%10s%10s%10s%10s\n",course.getCname(),course.getCourseNum(),course.getTeacher(),course.getTime());
        while (true){
            System.out.println("请输入修改的参数:");
            System.out.println("1. 课程号 2. 课程名称 3. 任课教师 4.学时(按0取消 按5提交内容)");
            num=in.next();
            switch (num){
                case "0":updateCourse();break;
                case "1":course.setCourseNum((String) updateCoursePro(num));break;
                case "2":course.setCname((String) updateCoursePro(num));break;
                case "3":course.setTeacher((String) updateCoursePro(num));break;
                case "4":course.setTime(Float.parseFloat((String)updateCoursePro(num)));break;
                case "5":
                    if(service.updateCourse(course)){
                        System.out.println("修改成功");
                        updateCourse();
                        return;
                    }else{
                        System.out.println("修改后的课程号重复");
                    }
            }
        }
    }

    /**
     * 修改课程的具体属性
     */
    public static Object updateCoursePro(String num){
        String data;
        switch (num){
            case "1":
                System.out.println("请输入修改后的课程号:");
                data=in.next();
                return data;
            case "2":
                System.out.println("请输入修改后的名称:");
                data=in.next();
                return data;
            case "3":
                System.out.println("请输入修改后的任课教师:");
                data=in.next();
                return data;
            case "4":
                System.out.println("请输入修改后的学时");
                data=in.next();
                return data;
        }
        return null;
    }
}
