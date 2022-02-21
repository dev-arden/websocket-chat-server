package com.websocket.chat.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.websocket.chat.model.ChatRoom;

@Mapper
public interface ChatMapper {
	public List<ChatRoom> findAllRoom();
	public ChatRoom findRoomById(@Param("roomId") String roomId);
	public void createRoom(@Param("roomId") String roomId, @Param("name") String name, @Param("ownerId") String ownerId);
	public String findOwner(@Param("roomId") String roomId);
	public ChatRoom findRoomOwnerById(@Param("roomId") String roomId, @Param("ownerId") String ownerId);
}
