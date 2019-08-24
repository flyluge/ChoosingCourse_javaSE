package com.lu.ui;

import com.lu.domain.Admin;
import com.lu.service.AdminService;

import java.util.Scanner;

/**
 * @Author: Luge
 * @DATE: 2019/8/21 16:23
 */
public class AdminUI {
    static final AdminService adminService=new AdminService();
    static Admin admin=null;
    static final Scanner in=new Scanner(System.in);
    public static void adminmainFrame() {
    	System.out.println("欢迎来到管理员界面!");
    	System.out.println("0. 返回主界面");
    	System.out.println("1. 登陆");
    	System.out.println("2. 注册");
    	switch(in.next()) {
    		case "0": MainFrameUI.showMainFrame();break;
    		case "1": loginUI();break;
    		case "2": registUI();break;
    		default:adminmainFrame();
    	}
    }
    public static void loginUI(){
        while(true){
            System.out.println("当前位置:管理员登陆");
            String num;
            String password;
            System.out.println("请输入账号:(输入exit取消登陆)");
            num=in.next();
            if(num.equals("exit")){
            	adminmainFrame();
                return;
            }
            System.out.println("请输入密码:");
            password=in.next();
            admin = adminService.findByNumAndPass(num, password);
            if(admin!=null){
                adminSelectUI();
                break;
            }else{
                System.out.println("用户名或密码不正确,请重新登陆");
            }
        }
    }
    public static void registUI() {
    	while(true) {
	    	System.out.println("当前位置:管理员注册");
	    	Admin a=new Admin();
	    	System.out.println("请输入账号:(输入exit取消注册)");
	    	String num=in.next();
	    	if(num.equals("exit")) {
	    		adminmainFrame();
	    		return;
	    	}
	    	a.setNum(num);
	    	System.out.println("请输入姓名");
	    	a.setName(in.next());
	    	System.out.println("请输入密码");
	    	a.setPassword(in.next());
	    	if(adminService.add(a)) {
	    		System.out.println("注册成功,请登陆");
	    		loginUI();
	    		return;
	    	}else {
	    		System.out.println("注册失败,账号已存在");
	    	}
    	}
    }
    public static void adminSelectUI(){
        System.out.println("欢迎"+admin.getName());
        System.out.println("请选择要进行的操作");
        System.out.println("0. 退出");
        System.out.println("1. 学生管理");
        System.out.println("2. 课程管理");
        String num=in.next();
        switch (num){
            case "0":MainFrameUI.showMainFrame(); break;
            case "1":AdminStuUI.adminStuUI(); break;
            case "2":AdminCourseUI.adminCourseUI(); break;
            default:
                adminSelectUI();
        }
    }

}
