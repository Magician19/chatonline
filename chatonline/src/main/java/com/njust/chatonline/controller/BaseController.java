package com.njust.chatonline.controller;

import com.njust.chatonline.entity.User;
import com.njust.chatonline.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.soap.Addressing;

@Controller
public class BaseController {
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String Index() {
        return "login";
    }

    @RequestMapping("/login")
    public String Login(String username, String password) {
        if (userService.Login(username, password)) {
            return "index";
        }
        return "register";
    }

    @RequestMapping("/register")
    public String Register(User user) {
        return "login";
    }

    @RequestMapping("/chatroom")
    public String Chatroom(){
        return "chatroom";
    }
}
