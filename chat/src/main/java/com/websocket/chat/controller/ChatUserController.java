package com.websocket.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.websocket.chat.model.ChatMessage;
import com.websocket.chat.service.ChatRoomService;
import com.websocket.chat.service.ChatUserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/chat")
public class ChatUserController {
	@Autowired
	private final SimpMessageSendingOperations messagingTemplate;
	
	@Autowired
	private ChatUserService chatuserservice;
	@Autowired
	private ChatRoomService chatroomservice;
	
	
	@PostMapping("/room/user")
	@ResponseBody
	public void askPermission(ChatMessage message, @RequestParam String roomId, @RequestParam String userId) {
		message.setType(ChatMessage.MessageType.ENTER);
		message.setSender(userId);
		message.setRoomId(roomId);
		
		String creatorId = chatroomservice.findOwner(roomId);
		String name = chatroomservice.findRoomById(roomId).getName();
		
		ChatMessage usermessage = new ChatMessage();
		
		//creator가 신청하는 경우
		if(userId.equals(creatorId)) {
			message.setMessage(name +"방을 만든" + userId + "님은 입장을 요청할 수 없습니다.");
			message.setType(ChatMessage.MessageType.NOTI);
			messagingTemplate.convertAndSend("/topic/user/" + userId, message);
		}
		//이미 입장을 요청한 사람이 또 요청하는 경우
		else if(chatuserservice.checkRoom(roomId, userId) == 1){
			if(chatuserservice.checkAuth(roomId, userId) == 0) {
				message.setMessage(userId + "님은 " + name + "에 이미 입장을 요청했습니다.");
				message.setType(ChatMessage.MessageType.NOTI);
				messagingTemplate.convertAndSend("/topic/user/" + userId, message);
				return ;
			}else {
				message.setMessage(userId + "님은 " + name + "에 입장 가능합니다.");
				message.setType(ChatMessage.MessageType.NOTI);
				messagingTemplate.convertAndSend("/topic/user/" + userId, message);
				return ;
			}
		}else {
			usermessage.setMessage(userId + "님은" + name + "에 입장을 요청했습니다.");
			usermessage.setType(ChatMessage.MessageType.NOTI);
			messagingTemplate.convertAndSend("/topic/user/" + userId, usermessage);
			
			message.setMessage(userId + "님이 " + name + "에 입장을 요청합니다.");
			message.setType(ChatMessage.MessageType.ASK);
			chatuserservice.requestRoom(roomId, userId);
			messagingTemplate.convertAndSend("/topic/user/" + creatorId, message);
		}
	}
	
	@PutMapping("/room/user")
	@ResponseBody
	public void acceptPermission(@RequestParam String roomId, @RequestParam String userId) {
		String creatorId = chatroomservice.findOwner(roomId);
		
		String name = chatroomservice.findRoomById(roomId).getName();
		
		ChatMessage requestermessage = new ChatMessage();
		ChatMessage creatormessage = new ChatMessage();
		
		chatuserservice.acceptRoom(roomId,userId);
		
		requestermessage.setMessage(userId + "님은" + name + "에 입장이 허용되었습니다.");
		creatormessage.setMessage(creatorId + "님은" + userId +"를" + name + "에 입장 수락했습니다.");
		
		messagingTemplate.convertAndSend("/topic/user/" + userId, requestermessage);
		messagingTemplate.convertAndSend("/topic/user/" + creatorId, creatormessage);
	}
	
	@DeleteMapping("/room/{roomId}/user/{userId}")
	@ResponseBody
	public void denyPermission(ChatMessage message, @PathVariable String roomId, @PathVariable String userId) {
		String creatorId = chatroomservice.findOwner(roomId);
		
		String name = chatroomservice.findRoomById(roomId).getName();
		
		ChatMessage requestermessage = new ChatMessage();
		ChatMessage creatormessage = new ChatMessage();
		
		chatuserservice.denyRoom(roomId, userId);
		
		requestermessage.setMessage(userId + "님은" + name + "에 입장이 거절되었습니다.");
		creatormessage.setMessage(creatorId + "님은" + userId +"를" + name + "에 입장 거절했습니다.");
		
		messagingTemplate.convertAndSend("/topic/user/" + userId, requestermessage);
		messagingTemplate.convertAndSend("/topic/user/" + creatorId, creatormessage);
	}
}
