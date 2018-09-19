package com.njust.chatonline.dao;

import com.njust.chatonline.entity.Room;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface RoomMapper {
    int deleteByPrimaryKey(Integer roomid);

    int insert(Room record);

    int insertSelective(Room record);

    Room selectByPrimaryKey(Integer roomid);

    int updateByPrimaryKeySelective(Room record);

    int updateByPrimaryKey(Room record);

    List<Room> getAllRoom();

    Room selectById(int roomId);


}