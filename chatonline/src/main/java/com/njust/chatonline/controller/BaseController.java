package com.njust.chatonline.controller;


import com.njust.chatonline.entity.Room;
import com.njust.chatonline.entity.User;
import com.njust.chatonline.service.RoomService;
import com.njust.chatonline.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class BaseController {

    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private RoomService roomService;

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


    @RequestMapping("/chatroom")
    public String Chatroom(String roomId, String password, String username, Model model) {
        Room room = roomService.getRoomById(Integer.valueOf(roomId));
        if (room.getPassword().equals(password) || room.getPassword() == null || room.getPassword() == "") {
            model.addAttribute("roomId", roomId);
            model.addAttribute("username", username);
            return "chat";
        }
        return "index";
    }

    @RequestMapping("/registerFinish")
    public String RegisterFinish(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userService.Register(user);
        logger.info("账号 = {}, 密码 = {}", username, password);
        return "login";
    }

    @RequestMapping("/register")
    public String Register() {
        return "register";
    }

    @RequestMapping("/isExist")
    @ResponseBody
    public boolean isExist(String userName) {
        return userService.isExist(userName);
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    //返回所有房间信息
    @RequestMapping("/getAllRoom")
    @ResponseBody
    public List<Room> getAllRoom() {
        return roomService.getAllRoom();
    }

    @RequestMapping("/insertRoom")
    @ResponseBody
    public int insertRoom(String password) {
        return roomService.insertRoom(password);
    }


}
