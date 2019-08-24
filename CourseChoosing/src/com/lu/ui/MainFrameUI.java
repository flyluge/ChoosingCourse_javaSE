package com.lu.ui;

import java.util.Scanner;

/**
 * @Author: Luge
 * @DATE: 2019/8/21 16:42
 */
public class MainFrameUI {
    private static Scanner in=new Scanner(System.in);
    public static void showMainFrame(){
        System.out.println("欢迎进入学生选课系统,请选择用户类型(学生:1/管理员:2)");
        String select=in.next();
        if(select.equals("1")){
            StudentUI.loginUI();
        }else if(select.equals("2")){
            AdminUI.adminmainFrame();
        }else{
            showMainFrame();
        }
    }
    public static void main(String[] args) {
        MainFrameUI.showMainFrame();
    }
}
