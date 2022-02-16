package com.websocket.chat.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import com.websocket.chat.model.ChatMessage;
import com.websocket.chat.model.ChatRoom;
import com.websocket.chat.service.ChatRoomService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ChatController {
	
	@Autowired
	private ChatRoomService chatroomservice;

	private final SimpMessageSendingOperations messagingTemplate;

 @MessageMapping("/chat/message")
 public void message(ChatMessage message, ChatRoom room) {
     if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
    	 //chatroomservice.findOwner(room.getOwnerId())
    	 if(chatroomservice.findOwner(room.getRoomId()).equals(message.getSender())) {
    		 message.setMessage("[owner]" + message.getSender() + "님이 입장하셨습니다.");
    	 }else {
    		 message.setMessage(message.getSender() + "님이 입장하셨습니다.");
    	 }
     }
     messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
 }
}
