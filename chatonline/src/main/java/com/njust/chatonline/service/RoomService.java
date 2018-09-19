package com.njust.chatonline.service;

import com.njust.chatonline.entity.Room;

import java.util.List;

public interface RoomService {
    List<Room> getAllRoom();
    int insertRoom(Room room);
    Room getRoomById(int roomId);
    int addNum(int roomId);
    int subNum(int roomId);
    int delete(int roomId);
}
