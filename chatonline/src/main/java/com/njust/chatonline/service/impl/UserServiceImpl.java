package com.njust.chatonline.service.impl;

import com.njust.chatonline.dao.UserMapper;
import com.njust.chatonline.entity.User;
import com.njust.chatonline.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public boolean Login(String username, String password) {
        User user = userMapper.getUserByUsername(username);
        if (user == null){
            return false;
        }
        if(user.getPassword().equals(password)){
            return true;
        }
        return false;
    }

    @Override
    public int Register(User user) {
        return userMapper.insert(user);
    }

    @Override
    public boolean isExist(String username) {

        return userMapper.getUserByUsername(username) != null;
    }
}
