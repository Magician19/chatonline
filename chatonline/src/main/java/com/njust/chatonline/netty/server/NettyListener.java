package com.njust.chatonline.netty.server;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @Author: chenyi
 * @Email: 228112142@qq.com
 * @Description: netty随项目启动
 */
@WebListener
public class NettyListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.err.println("nettyListener Startup!");
        new Thread(){
            @Override
            public  void run(){
                try {
                    new ChatServer().start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

        System.err.println("nettyListener end!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}