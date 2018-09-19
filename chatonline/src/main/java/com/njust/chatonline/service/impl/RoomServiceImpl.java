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
    public int insertRoom(Room room) {
        roomMapper.insert(room);
        return room.getRoomid();
    }

    @Override
    public Room getRoomById(int roomId) {
        return roomMapper.selectByPrimaryKey(roomId);
    }

    @Override
    public int addNum(int roomId) {
        Room room = roomMapper.selectByPrimaryKey(roomId);
        room.setNumber(room.getNumber() + 1);
        return roomMapper.updateByPrimaryKey(room);
    }

    @Override
    public int subNum(int roomId) {
        Room room = roomMapper.selectByPrimaryKey(roomId);
        if (room.getNumber() > 0) {
            room.setNumber(room.getNumber() - 1);
        }
        return roomMapper.updateByPrimaryKey(room);
    }

    @Override
    public int delete(int roomId) {
        return roomMapper.deleteByPrimaryKey(roomId);
    }
}
