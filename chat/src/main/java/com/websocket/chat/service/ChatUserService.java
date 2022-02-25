package com.websocket.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websocket.chat.mapper.ChatUserMapper;

@Service
public class ChatUserService {
	@Autowired
	private ChatUserMapper chatusermapper;
	
	public void requestRoom(String roomId, String userId) {
		chatusermapper.requestRoom(roomId, userId);
	}
	
	public void acceptRoom(String roomId, String userId) {
		chatusermapper.acceptRoom(roomId, userId);
	}
	
	public void denyRoom(String roomId, String userId) {
		chatusermapper.denyRoom(roomId, userId);
	}

	public int checkRoom(String roomId, String userId) {
		return chatusermapper.checkRoom(roomId, userId);
	}
	
	public int checkAuth(String roomId, String userId) {
		return chatusermapper.checkAuth(roomId, userId);
	}
}
