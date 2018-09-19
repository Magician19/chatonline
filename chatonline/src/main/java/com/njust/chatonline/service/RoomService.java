package com.njust.chatonline.service;

import com.njust.chatonline.entity.Room;

import java.util.List;

public interface RoomService {
    List<Room> getAllRoom();
    int insertRoom(String password);
    Room getRoomById(int roomId);
}
