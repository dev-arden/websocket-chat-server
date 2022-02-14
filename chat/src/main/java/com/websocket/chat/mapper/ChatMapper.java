package com.websocket.chat.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.websocket.chat.model.ChatRoom;

@Mapper
public interface ChatMapper {
	public int test();
	
	public List<ChatRoom> findAllRoom();
	public ChatRoom findRoomById(String id);
	public ChatRoom createChatroom(ChatRoom chatRoom);
}
