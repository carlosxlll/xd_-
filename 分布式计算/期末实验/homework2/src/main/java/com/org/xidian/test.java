package com.org.xidian;

import net.minidev.json.JSONUtil;

public class test {
    public static void main(String[] args) {
        String s1 = "<";
        String s2 = "班级331";
        String s3 = "<"+s2;
        String s4 = s3.substring(0, 1);
        if(s4.equals("<")){
            System.out.println("wfdefdsdsf");
        }
        System.out.println(s4);
    }



}
