package com.websocket.chat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websocket.chat.mapper.ChatMapper;
import com.websocket.chat.model.ChatRoom;

@Service
public class ChatRoomService {
	 @Autowired
	 private ChatMapper chatmapper;

	// @PostConstruct
	// private void init() {
//	     //chatRoomMap = new LinkedHashMap<>();
//		 chatmapper = new ChatMapperImpl();
	// }

	 public List<ChatRoom> findAllRoom() {
	     // 채팅방 생성순서 최근 순으로 반환
//	     List chatRooms = new ArrayList<>(chatRoomMap.values());
//	     Collections.reverse(chatRooms);
//	     return chatRooms;
		 return chatmapper.findAllRoom();
	 }

	 public ChatRoom findRoomById(String roomId) {
	     //return chatRoomMap.get(id);
		 return chatmapper.findRoomById(roomId);
	 }

	 public void createRoom(String name, String ownerId) {
	     ChatRoom chatRoom = ChatRoom.create(name, ownerId);
//	     chatRoom.setName(chatRoom.getName());
//	     chatRoom.setRoomId(chatRoom.getRoomId());
	     //chatRoomMap.put(chatRoom.getRoomId(), chatRoom);
	     chatmapper.createRoom(chatRoom.getRoomId(), chatRoom.getName(), chatRoom.getOwnerId());
	     //return chatmapper.createChatroom(chatRoom.getRoomId(), chatRoom.getName());
	 }
	 
	 public String findOwner(String roomId) {
		 return chatmapper.findOwner(roomId);
	 }
	 
	 public ChatRoom findRoomOwnerById(String roomId, String ownerId) {
		 return chatmapper.findRoomOwnerById(roomId,ownerId);
	 }
}
