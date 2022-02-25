package com.websocket.chat.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import com.websocket.chat.model.ChatMessage;
import com.websocket.chat.model.ChatRoom;
import com.websocket.chat.repository.AccountRepository;
import com.websocket.chat.service.ChatRoomService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ChatController {
	
	@Autowired
	private ChatRoomService chatroomservice;
	
	@Autowired
	private final SimpMessageSendingOperations messagingTemplate;
	
	@Autowired
	private AccountRepository accountrepo;
 
	 @MessageMapping("/chat/message/{ownerId}")
	 public void askMessage(@DestinationVariable String ownerId, ChatMessage message, ChatRoom room) {
		 message.setMessage(message.getSender() + "님이 입장을 요청합니다.");
		
		 messagingTemplate.convertAndSend("/topic/chat/room" + message.getRoomId() + room.getOwnerId(), message);
	 }
	 
	 @MessageMapping("/chat/message")
	 public void message(ChatMessage message, ChatRoom room, @Header("id") String id) {
		 // 로그인 회원 정보로 대화명 설정
		 message.setSender(id);
		  
		 // 채팅방 입장시에는 대화명과 메시지를 자동으로 세팅한다.
	    if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
		    message.setSender("[알림]");
		    message.setMessage(id + "님이 입장하셨습니다.");
	    }
	     messagingTemplate.convertAndSend("/topic/chat/room/" + message.getRoomId(), message);	 
	 }
	 
	 @MessageMapping("/chat/private-message")
	 public void privatemessage(ChatMessage message, ChatRoom room, @Header("id") String id) {
		 // 로그인 회원 정보로 대화명 설정
		 message.setSender(id);
		  
		 // 채팅방 입장시에는 대화명과 메시지를 자동으로 세팅한다.
	    if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
		    message.setSender("[알림]");
		    message.setMessage(id + "님이 입장하셨습니다.");
	    }
	     messagingTemplate.convertAndSend("/topic/user/" + id, message);	 
	 }
}
