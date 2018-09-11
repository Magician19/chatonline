package com.njust.chatonline.service;

import com.njust.chatonline.entity.User;

public interface UserService {
    boolean Login(String username, String password);

    int Register(User user);

    boolean isExist(String username);
}
