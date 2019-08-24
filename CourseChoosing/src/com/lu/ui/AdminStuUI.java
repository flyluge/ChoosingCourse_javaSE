package com.lu.ui;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.lu.domain.Student;
import com.lu.service.StudentService;

/**
 * 学生管理界面
 * @Author: Luge
 * @DATE: 2019/8/21 17:26
 */
public class AdminStuUI {
    /*输入流*/
    static final Scanner in=new Scanner(System.in);
    /*学生服务类*/
    static final StudentService service=new StudentService();

    /**
     * 学生管理主界面
     */
    public static void adminStuUI(){
        System.out.println("当前位置:学生管理");
        System.out.println("请选择要进行的操作");
        System.out.println("0. 返回到管理员主界面");
        System.out.println("1. 查看学生");
        System.out.println("2. 新增学生");
        System.out.println("3. 修改学生");
        System.out.println("4. 删除学生");
        String num=in.next();
        switch (num){
            case "0":AdminUI.adminSelectUI(); break;
            case "1":showStudent(); break;
            case "2":addStu(); break;
            case "3":updateStu(); break;
            case "4": deleteStu();break;
            default:
                AdminUI.adminSelectUI();
        }
    }

    /**
     * 学生信息显示方式的选择列表
     */
    public static void showStudent(){
        System.out.println("当前位置:学生管理->查看学生");
        System.out.println("请选择要进行的操作");
        System.out.println("0. 返回上一层");
        System.out.println("1. 根据学生姓名获取信息");
        System.out.println("2. 查看所有学生信息");
        String num=in.next();
        switch (num){
            case "0": adminStuUI(); break;
            case "1": showOneStu(); break;
            case "2": showAllStu();break;
            default:
                adminStuUI();break;
        }
    }

    /**
     * 根据姓名条件显示学生信息
     */
    public static void showOneStu(){
        System.out.println("当前位置:学生管理->查看学生->根据姓名获取学生信息");
        System.out.println("请输入学生姓名:(按0返回上一层)");
        String name=in.next();
        if(name.equals("0")){
            showStudent();
            return;
        }
        List<?> list = service.findByName(name);
        if(list.size()==0){
            System.out.println("未查找到学生!");
            showOneStu();
            return;
        }else{
            System.out.println("学生姓名:\t账号:\t性别:\t");
            for (Object obj:list) {
                Student s=(Student)obj;
                System.out.println(s.getStuname()+"\t"+s.getNum()+"\t"+s.getSex()+"\t");
            }
            System.out.println("按1继续查找 按0返回上一层");
            name=in.next();
            switch (name){
                case "0":showStudent();break;
                case "1":showOneStu();break;
                default:adminStuUI();break;
            }
        }
    }
    /**
     * 显示所有学生信息
     */
    public static void showAllStu(){
        System.out.println("当前位置:学生管理->查看学生->所有学生信息");
        System.out.println("所有学生信息如下:");
        Map<?, ?> map = service.findAll();
        if(map==null){
            System.out.println("请选择要进行的操作");
            System.out.println("还未添加学生,请先添加学生(按1跳转到添加学生界面)");
        }else{
            System.out.println("学生姓名:\t账号:\t性别:\t");
            for(Object obj:map.keySet()){
                Student s=(Student) map.get(obj);
                System.out.println(s.getStuname()+"\t"+s.getNum()+"\t"+s.getSex()+"\t");
            }
            System.out.println("请选择要进行的操作");
            System.out.println("按1添加学生 按2删除学生");
        }

        System.out.println("按0返回上一层");
        String num=in.next();
        switch (num){
            case "0":showStudent(); break;
            case "1":addStu();break;
            case "2":deleteStu();break;
            default:
                adminStuUI();
        }
    }

    /**
     * 添加学生信息
     */
    public static void addStu(){
        System.out.println("当前位置:学生管理->添加学生");
        System.out.println("请输入学生姓名:(输入0返回上一层)");
        Student student=new Student();
        String name=in.next();
        student.setStuname(name);
        if(name.equals("0")){
            adminStuUI();
            return;
        }
        System.out.println("请输入学生学号:");
        student.setNum(in.next());
        System.out.println("请输入学生密码:");
        student.setPassword(in.next());
        System.out.println("请输入学生性别:");
        student.setSex(in.next());
        if(service.add(student)){
            System.out.println("添加成功,请填写下一位学生信息");
            addStu();
        }else{
            System.out.println("学生账号已存在,请重写输入");
            addStu();
        }
    }

    /**
     * 删除学生信息
     */
    public static void deleteStu(){
        System.out.println("当前位置:学生管理->删除学生");
        System.out.println("请输入要删除学生的学号(按0返回上一层)");
        String num=in.next();
        if(num.equals("0")){
            adminStuUI();
            return;
        }
        if(service.deleteStuByNum(num)){
            System.out.println("删除成功");
            deleteStu();
        }else{
            System.out.println("该学生不存在");
            deleteStu();
        }
    }

    /**
     * 更新学生
     */
    public static void updateStu(){
        System.out.println("当前位置:学生管理->修改学生");
        System.out.println("请输入要修改学生的学号(按0返回上一层)");
        String num=in.next();
        if(num.equals("0")){
            adminStuUI();
            return;
        }
        Student student=service.findStuByNum(num);
        //修改的学生不存在 重写刷新界面
        if(student==null){
            System.out.println("要修改的学生不存在!");
            updateStu();
            return;
        }
        System.out.println("要修改学生的信息如下:");
        System.out.println("学号:\t姓名\t密码\t");
        System.out.println(student.getNum()+"\t"+student.getStuname()+"\t"+"******");
        while (true){
            System.out.println("请输入修改的参数:");
            System.out.println("1. 学号 2. 姓名 3. 密码 4. 性别(按0取消 按5提交内容)");
            num=in.next();
            switch (num){
                case "0":updateStu();break;
                case "1":student.setNum((String) updateStuPro(num));break;
                case "2":student.setStuname((String) updateStuPro(num));break;
                case "3":student.setPassword((String) updateStuPro(num));break;
                case "4":student.setSex((String) updateStuPro(num));break;
                case "5":
                    if(service.updateStu(student)){
                        System.out.println("修改成功");
                        updateStu();
                        return;
                    }else{
                        System.out.println("修改后的学号重复");
                    }
            }
        }
    }

    /**
     * 修改学生的具体属性
     */
    public static Object updateStuPro(String num){
        String data;
        switch (num){
            case "1":
                System.out.println("请输入修改后的学号:");
                data=in.next();
                return data;
            case "2":
                System.out.println("请输入修改后的姓名:");
                data=in.next();
                return data;
            case "3":
                System.out.println("请输入修改后的密码:");
                data=in.next();
                return data;
            case "4":
                System.out.println("请输入修改后的性别");
                data=in.next();
                return data;
        }
        return null;
    }
}
