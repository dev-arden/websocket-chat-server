package com.websocket.chat.repo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.websocket.chat.mapper.ChatMapper;
import com.websocket.chat.model.ChatRoom;

@Repository
public class ChatRoomRepository {

 private Map<String, ChatRoom> chatRoomMap;
 @Autowired
 private ChatMapper chatmapper;

// @PostConstruct
// private void init() {
//     //chatRoomMap = new LinkedHashMap<>();
//	 chatmapper = new ChatMapperImpl();
// }

 public List<ChatRoom> findAllRoom() {
     // 채팅방 생성순서 최근 순으로 반환
//     List chatRooms = new ArrayList<>(chatRoomMap.values());
//     Collections.reverse(chatRooms);
//     return chatRooms;
	 return chatmapper.findAllRoom();
 }

 public ChatRoom findRoomById(String id) {
     //return chatRoomMap.get(id);
	 return chatmapper.findRoomById(id);
 }

 public void createChatRoom(String name) {
     ChatRoom chatRoom = ChatRoom.create(name);
//     chatRoom.setName(chatRoom.getName());
//     chatRoom.setRoomId(chatRoom.getRoomId());
     //chatRoomMap.put(chatRoom.getRoomId(), chatRoom);
     chatmapper.createChatroom(chatRoom.getRoomId(), chatRoom.getName());
     //return chatmapper.createChatroom(chatRoom.getRoomId(), chatRoom.getName());
 }
}
