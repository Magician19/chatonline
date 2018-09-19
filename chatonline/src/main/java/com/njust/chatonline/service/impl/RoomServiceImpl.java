package com.njust.chatonline.service.impl;

import com.njust.chatonline.dao.RoomMapper;
import com.njust.chatonline.entity.Room;
import com.njust.chatonline.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomMapper roomMapper;
    @Override
    public List<Room> getAllRoom() {
        return roomMapper.getAllRoom();
    }

    @Override
    public int insertRoom(String password) {
        return roomMapper.insertRoom(password);
    }

    @Override
    public Room getRoomById(int roomId) {
        return roomMapper.selectByPrimaryKey(roomId);
    }
}
