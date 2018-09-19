package com.njust.chatonline.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Test implements Cloneable {
    public static void main(String[] args) {
//        try {
//            Process pr = Runtime.getRuntime().exec("python C:\\Users\\Kevin\\Desktop\\Haze-Removal-master\\HazeRemoval.py");
//            BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
//            String line;
//            while ((line = in.readLine()) != null) {
//                System.out.println(line);
//            }
//            in.close();
//            pr.waitFor();
//            System.out.println("end");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        try {
            //需传入的参数
            String url = "http://47.100.58.159/img/test.png";
            //设置命令行传入参数
             args = new String[] { "python", "C:\\Users\\Kevin\\Desktop\\Haze-Removal-master\\HazeRemoval.py", url };
            Process pr = Runtime.getRuntime().exec(args);

            BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            pr.waitFor();
            System.out.println("end");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

